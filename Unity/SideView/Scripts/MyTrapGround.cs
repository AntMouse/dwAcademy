using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class MyTrapGround : MonoBehaviour
{
    public GameObject objPrefab; // ���뿡�� �߻�Ǵ� ��ź Prefab
    public float delayTime = 3.0f; // ���� �ð�
    public float fireSpeedX = -4.0f; // �߻� ���� X
    public float fireSpeedY = 0.0f; // �߻� ���� Y
    public float length = 5.0f;

    GameObject player; // �÷��̾�
    GameObject gateObj; // �߻籸
    float passedTimes = 0; // ����ð�

    // �������� �� �߰�
    public GameObject targetMoveBlock; // �������� ��

    // Start is called before the first frame update
    void Start()
    {
        // �߻籸 ������Ʈ ���
        Transform tr = transform.Find("gate");
        gateObj = tr.gameObject;
        // �÷��̾�
        player = GameObject.FindGameObjectWithTag("Player");
    }

    // Update is called once per frame
    void Update()
    {
        // �߻� �ð� ����
        passedTimes += Time.deltaTime;
        // �Ÿ� Ȯ��
        if (CheckLength(player.transform.position))
        {
            Rigidbody2D movGround = targetMoveBlock.GetComponent<Rigidbody2D>();
            movGround.bodyType = RigidbodyType2D.Dynamic;
        }
    }

    // �Ÿ� Ȯ��
    bool CheckLength(Vector2 targetPos)
    {
        bool ret = false;
        float d = Vector2.Distance(transform.position, targetPos);
        if (length >= d)
        {
            ret = true;
        }
        return ret;
    }
}
