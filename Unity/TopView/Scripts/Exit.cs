﻿using System.Collections;
using System.Collections.Generic;
using UnityEngine;

//출입구 위치
public enum ExitDirection
{
    right,  //오른쪽
    left,   //왼쪽
    down,   //아래쪽
    up,     //위쪽
}

public class Exit : MonoBehaviour
{
    public string sceneName = "";   //이동할 씬 이름
    public int doorNumber = 0;      //문 번호. 내가 이동할 씬의 몇 번째 문.
    public ExitDirection direction = ExitDirection.down;//문의 위치

    // Start is called before the first frame update
    void Start()
    {

    }

    // Update is called once per frame
    void Update()
    {

    }
    private void OnTriggerEnter2D(Collider2D collision)
    {
        if (collision.gameObject.tag == "Player")
        {
            if (doorNumber == 100)
            {
                // 게임 클리어
                GameObject.FindObjectOfType<UIManager>().GameClear();

            }
            else
            {
                string nowScene = PlayerPrefs.GetString("LastScene");
                SaveDataManager.SaveArrangeData(nowScene); // 배치 데이터 저장
                RoomManager.ChangeScene(sceneName, doorNumber);
            }
        }
    }
}

