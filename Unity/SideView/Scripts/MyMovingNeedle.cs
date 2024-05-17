using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class MyMovingNeedle : MonoBehaviour
{
    public float moveX = 0.0f; // X 이동 거리
    public float moveY = 0.0f; // Y 이동 거리
    public float times = 0.0f; // 시간
    public float weight = 0.0f; // 정지 시간
    public bool isMoveWhenOn = false; // 올라갔을 때 움직이기

    public bool isCanMove = true; // 움직임
    float perDX; // 1 프레임당 X 이동 값
    float perDY; // 1 프레임당 Y 이동 값
    // fixedUpdate에 코드가 있기 때문에 프레임 마다 작동하는 게 아니라 0.02초마다 작동한다.
    Vector3 defPos; // 초기 위치
    bool isReverse = false; // 반전 여부

    private float actionTimer = 0.0f; // 최초 실행 1.5초 대기

    // Start is called before the first frame update
    void Start()
    {
        // 초기 위치
        defPos = transform.position;
        // 1 프레임에 이동하는 시간
        float timestep = Time.fixedDeltaTime;
        // 동일하게 0.02초. fixed니까. DeltaTime는 업데이트와 업데이트마다 사이의 시간.
        // 1 프레임의 X 이동 값
        // fixedUpdate 호출시 마다 이동해야하는 X 이동 값.
        // perDX = moveX / (1.0f / timestep * times);
        // perDX = 0.02초마다 이동해야하는 거리. (moveX / times) << 초당 이동 거리. * timestep를 했으니 0.02초마다 가는 거리가 나온다.
        perDX = (moveX / times) * timestep;
        // 1 프레임의 Y 이동 값
        // perDY = moveY / (1.0f / timestep * times);
        perDY = (moveY / times) * timestep;

        if (isMoveWhenOn)
        {
            // 처음에는 움직이지 않고 올라가면 움직이기 시작
            isCanMove = false;
        }
    }

    // Update is called once per frame
    void Update()
    {

    }

    private void FixedUpdate()
    {
        if (actionTimer < 2.5f)
        {
            actionTimer += Time.deltaTime;
        }

        if (isCanMove && actionTimer >= 1.5f)
        {
            // 이동 중
            float y = transform.position.y;
            bool endY = false;
            if (isReverse)
            {
                if ((perDY >= 0.0f && y <= defPos.y) || (perDY < 0.0f && y >= defPos.y))
                {
                    endY = true; // Y 방향 이동 종료
                }
                // 블록 이동
                transform.Translate(new Vector3(-perDX, -perDY, defPos.z));
            }
            else
            {
                if ((perDY >= 0.0f && y >= defPos.y + moveY) || (perDY < 0.0f && y <= defPos.y + moveY))
                {
                    endY = true; // Y 방향 이동 종료
                }
                // 블록 이동
                Vector3 v = new Vector3(perDX, perDY, defPos.z);
                transform.Translate(v);
            }

            if (endY)
            {
                // 이동 종료
                if (isReverse)
                {
                    // 위치가 어긋나는 것을 방지하고자 정면 방향 이동으로 돌아가기 전에 초기 위치로 돌리기
                    transform.position = defPos;
                }
                isReverse = !isReverse; // 값을 반전시키기
                isCanMove = false; // 이동 가능 값을 false
                if (isMoveWhenOn == false)
                {
                    // 올라갔을 때 움직이는 값이 꺼진 경우
                    Invoke("Move", weight); // weight만큼 지연 후 다시 이동
                }
            }
        }
    }

    // 이동하게 만들기
    public void Move()
    {
        isCanMove = true;
    }

    // 이동하지 못하게 만들기
    public void Stop()
    {
        isCanMove = false;
    }
}
