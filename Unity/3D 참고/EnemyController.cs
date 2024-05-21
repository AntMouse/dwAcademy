using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.AI;

public class EnemyController : MonoBehaviour
{
    public Transform player; // �÷��̾�
    public float chaseDistance = 10f; // ���� �÷��̾ �Ѿƿ��� �����ϴ� �Ÿ�
    public float stopDistance = 2f; // ���� �÷��̾�� ������ �ٰ����� �� ���ߴ� �Ÿ�
    public float moveSpeed = 3f; // ���� �̵��ӵ�

    private NavMeshAgent agent; // �̵��� �갡 �˾Ƽ���. rigidbody �ʿ� ����.
    private float distanceToPlayer; // �÷��̾���� �Ÿ��� ����ϴ� ����

    // Start is called before the first frame update
    void Start()
    {
        agent = GetComponent<NavMeshAgent>();
        agent.speed = moveSpeed;
    }

    // Update is called once per frame
    void Update()
    {
        // �÷��̾���� �Ÿ� ���
        distanceToPlayer = Vector3.Distance(player.position, transform.position);

        if (distanceToPlayer <= chaseDistance)
        {
            if (distanceToPlayer > stopDistance) // �÷��̾ ���� �̵�
            {
                agent.SetDestination(player.position);
            }
            else // �÷��̾���� �Ÿ��� stopDistance���� ������ ����
            {
                agent.SetDestination(transform.position);
            }
        }
        else
        {
            // �÷��̾ ���� �Ÿ� �ۿ� �����Ƿ� ����
            agent.SetDestination(transform.position);
        }
    }
}
