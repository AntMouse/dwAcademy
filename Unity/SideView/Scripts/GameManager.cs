using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI; // UI�� ����� �� �ʿ�

public class GameManager : MonoBehaviour
{
    public GameObject mainImage; // �̹����� ��Ƶδ� GameObject
    public Sprite gameOverSpr; // GAME OVER �̹���
    public Sprite gameClearSpr; // GAME CLEAR �̹���
    public GameObject panel; // �г�
    public GameObject restartButton; // RESTART ��ư
    public GameObject nextButton; // NEXT ��ư

    Image titleImage; // �̹����� ǥ���ϴ� Image ������Ʈ

    // +++ ���� �߰� +++
    public GameObject scoreText; // ���� �ؽ�Ʈ
    public static int totalScore; // ���� ����
    public int stageScore = 0; // �������� ����

    // +++ ���� ��� �߰� +++
    public AudioClip meGameOver; // ���� ����
    public AudioClip meGameClear; // ���� Ŭ����

    // +++ �÷��̾� ���� +++
    public GameObject inputUI; // ���� UI �г�

    // Start is called before the first frame update
    void Start()
    {
        // �̹��� �����
        // �Լ��� �̸��� ������. 1�� �ڿ� �ش� �Լ��� �θ���. �񵿱� �Լ�. ����� ����� ���ÿ� �߻����� ����.
        Invoke("InactiveImage", 1.0f);
        // ��ư(�г�)�� �����
        panel.SetActive(false);

        // +++ ���� �߰� +++
        UpdateScore();
    }

    // Update is called once per frame
    void Update()
    {
        if (PlayerController.gameState == "gameclear")
        {
            // ���� Ŭ����
            mainImage.SetActive(true); // �̹��� ǥ��
            panel.SetActive(true); // ��ư(�г�)�� ǥ��
            // RESTART ��ư ��ȿȭ
            Button bt = restartButton.GetComponent<Button>();
            bt.interactable = false; // ��ȣ �ۿ��� ��Ȱ��ȭ.
            mainImage.GetComponent<Image>().sprite = gameClearSpr;
            PlayerController.gameState = "gameend";

            // +++ ���� �߰� +++
            totalScore += stageScore;
            stageScore = 0;
            UpdateScore(); // ���� ����

            // +++ ���� ��� �߰� +++
            // ���� ���
            AudioSource soundPlayer = GetComponent<AudioSource>();
            if (soundPlayer != null)
            {
                // BGM ����
                soundPlayer.Stop();
                soundPlayer.PlayOneShot(meGameClear);
            }

            // +++ �÷��̾� ���� +++
            inputUI.SetActive(false); // ���� UI �����
        }
        else if (PlayerController.gameState == "gameover")
        {
            // ���� ����
            mainImage.SetActive(true); // �̹��� ǥ��
            panel.SetActive(true); // ��ư(�г�)�� ǥ��
            // NEXT ��ư�� ��Ȱ��
            Button bt = nextButton.GetComponent<Button>();
            bt.interactable = false;
            mainImage.GetComponent<Image>().sprite = gameOverSpr;
            PlayerController.gameState = "gameend";

            // +++ ���� ��� �߰� +++
            // ���� ���
            AudioSource soundPlayer = GetComponent<AudioSource>();
            if (soundPlayer != null)
            {
                // BGM ����
                soundPlayer.Stop();
                soundPlayer.PlayOneShot(meGameOver);
            }

            // +++ �÷��̾� ���� +++
            inputUI.SetActive(false); // ���� UI�����
        }
        else if (PlayerController.gameState == "playing")
        {
            // ���� ��
            GameObject player = GameObject.FindGameObjectWithTag("Player");
            // PlayerController ��������
            PlayerController playerCnt = player.GetComponent<PlayerController>();
            // +++ ���� �߰� +++
            if (playerCnt.score != 0)
            {
                stageScore += playerCnt.score;
                playerCnt.score = 0;
                UpdateScore();
            }
        }
    }
    void InactiveImage()
    {
        mainImage.SetActive(false);
    }

    // +++ ���� �߰� +++
    void UpdateScore()
    {
        int score = stageScore + totalScore;
        scoreText.GetComponent<Text>().text = score.ToString();
    }

    // +++ �÷��̾� ���� +++
    // ����
    public void Jump()
    {
        GameObject player = GameObject.FindGameObjectWithTag("Player");
        PlayerController playerCnt = player.GetComponent<PlayerController>();
        playerCnt.Jump();
    }

    public void GameExit()
    {
#if UNITY_EDITOR
        UnityEditor.EditorApplication.isPlaying = false;
#else
        Application.Quit();
#endif
    }
}
