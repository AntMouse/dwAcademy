using System.Collections;
using System.Collections.Generic;
using UnityEngine;

[System.Serializable]
/*
직렬화시킴. 기계는 직렬화된 자료를 보는 게 편해서.
아래 데이터를 컴퓨터의 파일에 직접 데이터화해서 저장해야해서.
그래서 그것과 관련된 준비를 해놓으라는 의미.
*/
public class SaveData
{
    public int arrangeId = 0;       //배치 ID
    public string objTag = "";      //배치된 오브젝트의 태그
}

[System.Serializable]
public class SaveDataList
{
    public SaveData[] saveDatas;    //SaveData 배열
}
