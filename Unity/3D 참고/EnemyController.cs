using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.AI;

public class EnemyController : MonoBehaviour
{
    public Transform player; // 플레이어
    public float chaseDistance = 10f; // 적이 플레이어를 쫓아오기 시작하는 거리
    public float stopDistance = 2f; // 적이 플레이어에게 가까이 다가갔을 때 멈추는 거리
    public float moveSpeed = 3f; // 적의 이동속도

    private NavMeshAgent agent; // 이동은 얘가 알아서함. rigidbody 필요 없음.
    private float distanceToPlayer; // 플레이어와의 거리를 계산하는 변수

    // Start is called before the first frame update
    void Start()
    {
        agent = GetComponent<NavMeshAgent>();
        agent.speed = moveSpeed;
    }

    // Update is called once per frame
    void Update()
    {
        // 플레이어와의 거리 계산
        distanceToPlayer = Vector3.Distance(player.position, transform.position);

        if (distanceToPlayer <= chaseDistance)
        {
            if (distanceToPlayer > stopDistance) // 플레이어를 향해 이동
            {
                agent.SetDestination(player.position);
            }
            else // 플레이어와의 거리가 stopDistance보다 가까우면 멈춤
            {
                agent.SetDestination(transform.position);
            }
        }
        else
        {
            // 플레이어가 추적 거리 밖에 있으므로 멈춤
            agent.SetDestination(transform.position);
        }
    }
}
