using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayerController : MonoBehaviour
{
    Rigidbody2D rbody; // Rigidbody2D형 변수
    float axisH = 0.0f; // 입력
    public float speed = 3.0f; // 이동 속도

    public float jump = 6.0f; // 점프력
    public LayerMask groundLayer; // 착지할 수 있는 레이어
    bool goJump = false; // 점프 개시 플래그
    bool onGround = false; // 지면에 서 있는 플래그

    // Start is called before the first frame update
    // MonoBehaviour을 상속 받음(: << 이걸로). 이걸 상속받으면 스프링에서 @ 이것처럼 알아서 실행해줌. 첫 번째 프레임 시작 전에 실행.

    // 애니메이션 처리
    Animator animator; // 애니메이터
    public string stopAnime = "PlayerIdle";
    public string moveAnime = "PlayerRun";
    public string jumpAnime = "PlayerJump";
    public string goalAnime = "PlayerClear";
    public string deadAnime = "GameOver";
    string nowAnime = "";
    string oldAnime = "";

    public static string gameState = "playing"; // 게임 상태

    public int score = 0; // 점수

    // 터치스크린 조작
    bool isMoving = false;

    void Start()
    {
        // Rigidbody2D 가져오기
        rbody = this.GetComponent<Rigidbody2D>();
        // Animator 가져오기
        animator = GetComponent<Animator>();
        nowAnime = stopAnime;
        oldAnime = stopAnime;

        gameState = "playing"; // 게임 중
    }

    // Update is called once per frame
    // 프레임마다 한 번씩 실행. 딜레이 가능성 있음.UI,이벤트 등 여기에 입력.
    void Update()
    {
        if (gameState != "playing")
        {
            return;
        }
        // 이동
        if (isMoving == false)
        {
            // 수평 방향의 입력 확인
            axisH = Input.GetAxisRaw("Horizontal");
        }

        // 수평 방향으로 입력 확인
        // 수평을 의미함
        // 패널 때문에 주석 처리로 변경 axisH = Input.GetAxisRaw("Horizontal");   
        // 방향 조절
        if(axisH > 0.0f)
        {
            // 오른쪽 이동
            // Debug.Log("오른쪽 이동");
            transform.localScale = new Vector2(1, 1);
            // 유니티에 있는 transform을 기리킴. rbody와 달리 getComponent 없이 그냥 가져올 수 있다.
        }
        else if (axisH < 0.0f)
        {
            // 왼쪽 이동
            // Debug.Log("왼쪽 이동");
            transform.localScale = new Vector2(-1, 1); // 좌우 반전시키기
        }

        // 캐릭터 점프하기
        if (Input.GetButtonDown("Jump"))
        {
            Jump(); // 점프
        }
    }

    void FixedUpdate()
    {
        if (gameState != "playing")
        {
            return;
        }
        // 착지 판정
        onGround = Physics2D.Linecast(transform.position,
                                      transform.position - (transform.up * 0.1f),
                                      groundLayer);

        if (onGround || axisH != 0)
        {
            // 지면 위 or 속도가 0 아님
            // 속도 갱신하기. 0.02마다 3의 속도로 움직임. (-)이면 < 이쪽. (+) 이면 >이쪽으로 움직임.
            // velocity 현재 이 플레이어가 가지고 있는 속도.
            rbody.velocity = new Vector2(speed * axisH, rbody.velocity.y);
        }
        if (onGround && goJump)
        {
            // 지면 위에서 점프 키 눌림
            // 점프하기
            Debug.Log(" 점프! ");
            Vector2 jumpPw = new Vector2(0, jump); // 점프를 위한 벡터 생성
            rbody.AddForce(jumpPw, ForceMode2D.Impulse); // 순간적인 힘 가하기
            goJump = false; // 점프 플래그 끄기
        }

        if (onGround)
        {
            // 지면 위
            if (axisH == 0)
            {
                nowAnime = stopAnime; // 정지
            }
            else
            {
                nowAnime = moveAnime; // 이동
            }
        }
        else
        {
            // 공중
            nowAnime = jumpAnime;
        }

        if (nowAnime != oldAnime)
        {
            oldAnime = nowAnime;
            animator.Play(nowAnime); // 애니메이션 재생
        }
    }
    // 점프
    public void Jump()
    {
        /*
        jumpCount = jumpCount + 1;
        if (jumpCount < 2)
        {

        }
        */
        goJump = true; // 점프 플래그 켜기
        Debug.Log(" 점프 버튼 눌림! ");
    }

    // 접촉 시작
    private void OnTriggerEnter2D(Collider2D collision)
    {
        Debug.Log(collision.gameObject.tag);

        // 접촉 시작
        if (collision.gameObject.tag == "Goal")
        {
            Goal(); // 골
        }
        else if (collision.gameObject.tag == "Dead")
        {
            GameOver(); // 게임 오버
        }
        else if (collision.gameObject.tag == "ScoreItem")
        {
            // 점수 아이템
            // ItemData 가져오기
            ItemData item = collision.gameObject.GetComponent<ItemData>();
            // 점수 얻기
            score = item.value;
            // 아이템 제거
            Destroy(collision.gameObject);
        }
    }

    // 골
    public void Goal()
    {
        animator.Play(goalAnime);
        gameState = "gameclear";
        GameStop(); // 게임 중지
    }
    // 게임 오버
    public void GameOver()
    {
        animator.Play(deadAnime);

        gameState = "gameover";
        GameStop(); // 게임 중지
        // ==================
        // 게임 오버 연출
        // ==================
        // 플레이어의 충돌 판정 비활성
        GetComponent<CapsuleCollider2D>().enabled = false;
        // 플레이어를 위로 튀어 오르게 하는 연출
        rbody.AddForce(new Vector2(0, 5), ForceMode2D.Impulse);
    }

    void GameStop()
    {
        // Rigidbody2D 가져오기
        Rigidbody2D rbody = GetComponent<Rigidbody2D>();
        // 속도를 0으로 하여 강제 정지
        rbody.velocity = new Vector2(0, 0);
    }

    // 터치스크린추가 구현
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
