using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayerController : MonoBehaviour
{
    public float moveSpeed = 5f; // �̵��ӵ�
    public float rotationSpeed = 720f; // ȸ���ҵ�
    public float inputThreshold = 0.1f; // �Ӱ谪

    private Rigidbody rb;
    private Vector3 moveDirection; // ���� ����

    // Start is called before the first frame update
    void Start()
    {
        rb = GetComponent<Rigidbody>();
        rb.constraints = RigidbodyConstraints.FreezeRotationX | RigidbodyConstraints.FreezeRotationZ;
    }

    // Update is called once per frame
    void Update()
    {
        float horizontal = Input.GetAxis("Horizontal"); // ����Ű(�¿����Ű A, D)
        float vertical = Input.GetAxis("Vertical"); // ����Ű (���Ʒ�, W S)

        moveDirection = new Vector3(horizontal, 0, vertical).normalized; // ������ �ӵ��� ��� ���� ���⸸ �˱� ���� ���

        // ���� ���� moveDirection�� 0�� �ƴ� ���ɼ��� ������ �� ���� �ʹ� ������ 0���� ����ؼ� ���߰� �Ѵ�.
        if (moveDirection.sqrMagnitude >= inputThreshold * inputThreshold)
        {
            // �̵��������� ȸ��
            Quaternion targetRotation = Quaternion.LookRotation(moveDirection); // ȸ����
            transform.rotation = Quaternion.RotateTowards(transform.rotation,
            targetRotation, rotationSpeed * Time.deltaTime); // õõ�� ȸ��
        }
        else
        {
            rb.angularVelocity = Vector3.zero;
        }
    }

    void FixedUpdate()
    {
        // �̵�
        if (moveDirection.sqrMagnitude >= inputThreshold * inputThreshold)
        {
            Vector3 move = moveDirection * moveSpeed;
            // x�� z�θ� �����̱� ������ y�� ���� �״�δ�. y���� ���� ���� ����.
            rb.velocity = new Vector3(move.x, rb.velocity.y, move.z);
        }
        else
        {
            rb.velocity = new Vector3(0, rb.velocity.y, 0);
        }
    }
}
