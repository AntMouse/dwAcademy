using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayerController : MonoBehaviour
{
    Rigidbody2D rbody; // Rigidbody2D�� ����
    float axisH = 0.0f; // �Է�
    public float speed = 3.0f; // �̵� �ӵ�

    public float jump = 6.0f; // ������
    public LayerMask groundLayer; // ������ �� �ִ� ���̾�
    bool goJump = false; // ���� ���� �÷���
    bool onGround = false; // ���鿡 �� �ִ� �÷���

    // Start is called before the first frame update
    // MonoBehaviour�� ��� ����(: << �̰ɷ�). �̰� ��ӹ����� ���������� @ �̰�ó�� �˾Ƽ� ��������. ù ��° ������ ���� ���� ����.

    // �ִϸ��̼� ó��
    Animator animator; // �ִϸ�����
    public string stopAnime = "PlayerIdle";
    public string moveAnime = "PlayerRun";
    public string jumpAnime = "PlayerJump";
    public string goalAnime = "PlayerClear";
    public string deadAnime = "GameOver";
    string nowAnime = "";
    string oldAnime = "";

    public static string gameState = "playing"; // ���� ����

    public int score = 0; // ����

    // ��ġ��ũ�� ����
    bool isMoving = false;

    void Start()
    {
        // Rigidbody2D ��������
        rbody = this.GetComponent<Rigidbody2D>();
        // Animator ��������
        animator = GetComponent<Animator>();
        nowAnime = stopAnime;
        oldAnime = stopAnime;

        gameState = "playing"; // ���� ��
    }

    // Update is called once per frame
    // �����Ӹ��� �� ���� ����. ������ ���ɼ� ����.UI,�̺�Ʈ �� ���⿡ �Է�.
    void Update()
    {
        if (gameState != "playing")
        {
            return;
        }
        // �̵�
        if (isMoving == false)
        {
            // ���� ������ �Է� Ȯ��
            axisH = Input.GetAxisRaw("Horizontal");
        }

        // ���� �������� �Է� Ȯ��
        // ������ �ǹ���
        // �г� ������ �ּ� ó���� ���� axisH = Input.GetAxisRaw("Horizontal");   
        // ���� ����
        if(axisH > 0.0f)
        {
            // ������ �̵�
            // Debug.Log("������ �̵�");
            transform.localScale = new Vector2(1, 1);
            // ����Ƽ�� �ִ� transform�� �⸮Ŵ. rbody�� �޸� getComponent ���� �׳� ������ �� �ִ�.
        }
        else if (axisH < 0.0f)
        {
            // ���� �̵�
            // Debug.Log("���� �̵�");
            transform.localScale = new Vector2(-1, 1); // �¿� ������Ű��
        }

        // ĳ���� �����ϱ�
        if (Input.GetButtonDown("Jump"))
        {
            Jump(); // ����
        }
    }

    void FixedUpdate()
    {
        if (gameState != "playing")
        {
            return;
        }
        // ���� ����
        onGround = Physics2D.Linecast(transform.position,
                                      transform.position - (transform.up * 0.1f),
                                      groundLayer);

        if (onGround || axisH != 0)
        {
            // ���� �� or �ӵ��� 0 �ƴ�
            // �ӵ� �����ϱ�. 0.02���� 3�� �ӵ��� ������. (-)�̸� < ����. (+) �̸� >�������� ������.
            // velocity ���� �� �÷��̾ ������ �ִ� �ӵ�.
            rbody.velocity = new Vector2(speed * axisH, rbody.velocity.y);
        }
        if (onGround && goJump)
        {
            // ���� ������ ���� Ű ����
            // �����ϱ�
            Debug.Log(" ����! ");
            Vector2 jumpPw = new Vector2(0, jump); // ������ ���� ���� ����
            rbody.AddForce(jumpPw, ForceMode2D.Impulse); // �������� �� ���ϱ�
            goJump = false; // ���� �÷��� ����
        }

        if (onGround)
        {
            // ���� ��
            if (axisH == 0)
            {
                nowAnime = stopAnime; // ����
            }
            else
            {
                nowAnime = moveAnime; // �̵�
            }
        }
        else
        {
            // ����
            nowAnime = jumpAnime;
        }

        if (nowAnime != oldAnime)
        {
            oldAnime = nowAnime;
            animator.Play(nowAnime); // �ִϸ��̼� ���
        }
    }
    // ����
    public void Jump()
    {
        /*
        jumpCount = jumpCount + 1;
        if (jumpCount < 2)
        {

        }
        */
        goJump = true; // ���� �÷��� �ѱ�
        Debug.Log(" ���� ��ư ����! ");
    }

    // ���� ����
    private void OnTriggerEnter2D(Collider2D collision)
    {
        Debug.Log(collision.gameObject.tag);

        // ���� ����
        if (collision.gameObject.tag == "Goal")
        {
            Goal(); // ��
        }
        else if (collision.gameObject.tag == "Dead")
        {
            GameOver(); // ���� ����
        }
        else if (collision.gameObject.tag == "ScoreItem")
        {
            // ���� ������
            // ItemData ��������
            ItemData item = collision.gameObject.GetComponent<ItemData>();
            // ���� ���
            score = item.value;
            // ������ ����
            Destroy(collision.gameObject);
        }
    }

    // ��
    public void Goal()
    {
        animator.Play(goalAnime);
        gameState = "gameclear";
        GameStop(); // ���� ����
    }
    // ���� ����
    public void GameOver()
    {
        animator.Play(deadAnime);

        gameState = "gameover";
        GameStop(); // ���� ����
        // ==================
        // ���� ���� ����
        // ==================
        // �÷��̾��� �浹 ���� ��Ȱ��
        GetComponent<CapsuleCollider2D>().enabled = false;
        // �÷��̾ ���� Ƣ�� ������ �ϴ� ����
        rbody.AddForce(new Vector2(0, 5), ForceMode2D.Impulse);
    }

    void GameStop()
    {
        // Rigidbody2D ��������
        Rigidbody2D rbody = GetComponent<Rigidbody2D>();
        // �ӵ��� 0���� �Ͽ� ���� ����
        rbody.velocity = new Vector2(0, 0);
    }

    // ��ġ��ũ���߰� ����
    public void SetAxis(float h, float v)
    {
        axisH = h;
        if (axisH == 0)
        {
            isMoving = false;
        }
        else
        {
            isMoving = true;
        }
    }
}
