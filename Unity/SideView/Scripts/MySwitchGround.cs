using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class MySwitchGround : MonoBehaviour
{
    // ����ġ �ڵ� �߰�
    public GameObject targetMoveBlock;
    public Sprite imageOn;
    public Sprite imageOff;
    public bool on = false; // ����ġ ����(true: ���� ���� false: ������ ���� ����)

    // Start is called before the first frame update
    void Start()
    {
        // ����ġ �ڵ� �߰�
        if (on)
        {
            GetComponent<SpriteRenderer>().sprite = imageOn;
        }
        else
        {
            GetComponent<SpriteRenderer>().sprite = imageOff;
        }
    }

    // Update is called once per frame
    void Update()
    {

    }

    // ���� ����
    private void OnTriggerEnter2D(Collider2D col)
    {
        if (col.gameObject.tag == "Player")
        {
            if (on)
            {
                on = false;
                GetComponent<SpriteRenderer>().sprite = imageOff;
            }
            else
            {
                on = true;
                GetComponent<SpriteRenderer>().sprite = imageOn;
                Rigidbody2D movGround = targetMoveBlock.GetComponent<Rigidbody2D>();
                movGround.bodyType = RigidbodyType2D.Dynamic;
            }
        }
    }
}
