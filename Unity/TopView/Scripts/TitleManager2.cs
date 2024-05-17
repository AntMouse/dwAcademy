using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class TitleManager2 : MonoBehaviour
{
    public GameObject startButton;
    public GameObject continueButton;

    // Start is called before the first frame update
    void Start()
    {
        startButton = GameObject.Find("StartButton");
        continueButton = GameObject.Find("ContinueButton");
        startButton.GetComponent<Button>().onClick.AddListener(startButtonClick);
        continueButton.GetComponent<Button>().onClick.AddListener(continueButtonClick);
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    void startButtonClick()
    {
        Debug.Log("StartButtonClick");
    }

    void continueButtonClick()
    {
        Debug.Log("ContinueButtonClick");
    }
}
