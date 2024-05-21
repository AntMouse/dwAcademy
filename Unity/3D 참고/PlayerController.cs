using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayerController : MonoBehaviour
{
    public float moveSpeed = 5f; // 이동속도
    public float rotationSpeed = 720f; // 회전소도
    public float inputThreshold = 0.1f; // 임계값

    private Rigidbody rb;
    private Vector3 moveDirection; // 방향 벡터

    // Start is called before the first frame update
    void Start()
    {
        rb = GetComponent<Rigidbody>();
        rb.constraints = RigidbodyConstraints.FreezeRotationX | RigidbodyConstraints.FreezeRotationZ;
    }

    // Update is called once per frame
    void Update()
    {
        float horizontal = Input.GetAxis("Horizontal"); // 수평키(좌우방향키 A, D)
        float vertical = Input.GetAxis("Vertical"); // 수직키 (위아래, W S)

        moveDirection = new Vector3(horizontal, 0, vertical).normalized; // 벡터의 속도는 상관 없고 방향만 알기 위해 사용

        // 손을 떼도 moveDirection이 0이 아닐 가능성이 있으니 이 값이 너무 작으면 0으로 취급해서 멈추게 한다.
        if (moveDirection.sqrMagnitude >= inputThreshold * inputThreshold)
        {
            // 이동방향으로 회전
            Quaternion targetRotation = Quaternion.LookRotation(moveDirection); // 회전각
            transform.rotation = Quaternion.RotateTowards(transform.rotation,
            targetRotation, rotationSpeed * Time.deltaTime); // 천천히 회전
        }
        else
        {
            rb.angularVelocity = Vector3.zero;
        }
    }

    void FixedUpdate()
    {
        // 이동
        if (moveDirection.sqrMagnitude >= inputThreshold * inputThreshold)
        {
            Vector3 move = moveDirection * moveSpeed;
            // x와 z로만 움직이기 때문에 y의 값을 그대로다. y값은 원래 값을 유지.
            rb.velocity = new Vector3(move.x, rb.velocity.y, move.z);
        }
        else
        {
            rb.velocity = new Vector3(0, rb.velocity.y, 0);
        }
    }
}
