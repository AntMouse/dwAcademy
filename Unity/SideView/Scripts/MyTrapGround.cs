using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class MyTrapGround : MonoBehaviour
{
    public GameObject objPrefab; // 포대에서 발사되는 포탄 Prefab
    public float delayTime = 3.0f; // 지연 시간
    public float fireSpeedX = -4.0f; // 발사 벡터 X
    public float fireSpeedY = 0.0f; // 발사 벡터 Y
    public float length = 5.0f;

    GameObject player; // 플레이어
    GameObject gateObj; // 발사구
    float passedTimes = 0; // 경과시간

    // 떨어지는 땅 추가
    public GameObject targetMoveBlock; // 떨어지는 땅

    // Start is called before the first frame update
    void Start()
    {
        // 발사구 오브젝트 얻기
        Transform tr = transform.Find("gate");
        gateObj = tr.gameObject;
        // 플레이어
        player = GameObject.FindGameObjectWithTag("Player");
    }

    // Update is called once per frame
    void Update()
    {
        // 발사 시간 판정
        passedTimes += Time.deltaTime;
        // 거리 확인
        if (CheckLength(player.transform.position))
        {
            Rigidbody2D movGround = targetMoveBlock.GetComponent<Rigidbody2D>();
            movGround.bodyType = RigidbodyType2D.Dynamic;
        }
    }

    // 거리 확인
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
