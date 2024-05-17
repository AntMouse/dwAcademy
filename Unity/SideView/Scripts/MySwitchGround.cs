using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class MySwitchGround : MonoBehaviour
{
    // 스위치 코드 추가
    public GameObject targetMoveBlock;
    public Sprite imageOn;
    public Sprite imageOff;
    public bool on = false; // 스위치 상태(true: 눌린 상태 false: 눌리지 않은 상태)

    // Start is called before the first frame update
    void Start()
    {
        // 스위치 코드 추가
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

    // 접촉 시작
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
