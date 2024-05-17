using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CameraManager : MonoBehaviour
{
    public float leftLimit = 0.0f; // ���� ��ũ�� ����
    public float rigthLimit = 0.0f; // ������ ��ũ�� ���� 
    public float topLimit = 0.0f; // �� ��ũ��  ����
    public float bottomLimit = 0.0f; // �Ʒ� ��ũ�� ����

    public GameObject subScreen; // ���� ��ũ��

    public bool isForceScrollX = false; // X�� ���� ��ũ�� �÷���
    public float forceScrollSpeedX = 0.5f; // 1�ʰ� ������ X�� �Ÿ�
    public bool isForceScrollY = false; // Y�� ���� ��ũ�� �÷���
    public float forceScrollSpeedY = 0.5f; // 1�ʰ� ������ Y�� �Ÿ�

    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        GameObject player = GameObject.FindGameObjectWithTag("Player"); // �÷��̾� ã��
        if (player != null)
        {
            // ī�޶��� ��ǥ ����
            float x = player.transform.position.x;
            float y = player.transform.position.y;
            float z = transform.position.z;
            // ���� ���� ����ȭ
            if (isForceScrollX)
            {
                // ���� ������ũ��
                x = transform.position.x + (forceScrollSpeedX * Time.deltaTime);
            }
            // �� ���� �̵� ���� ����
            if (x < leftLimit)
            {
                x = leftLimit;
            }
            else if (x > rigthLimit)
            {
                x = rigthLimit;
            }
            // ���� ���� ����ȭ
            if (isForceScrollY)
            {
                // ���� ���� ��ũ��
                y = transform.position.y + (forceScrollSpeedY * Time.deltaTime);
            }
            // ���Ʒ��� �̵� ���� ����
            if (y < bottomLimit)
            {
                y = bottomLimit;
            }
            else if (y > topLimit)
            {
                y = topLimit;
            }
            // ī�޶� ��ġ�� Vector3 �����
            Vector3 v3 = new Vector3(x, y, z);
            transform.position = v3;

            // ���� ��ũ�� ��ũ��
            if (subScreen != null)
            {
                y = subScreen.transform.position.y;
                z = subScreen.transform.position.z;
                Vector3 v = new Vector3(x / 2.0f, y, z);
                subScreen.transform.position = v;
            }
        }
    }
}