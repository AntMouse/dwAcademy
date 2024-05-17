function displayElementValue(id, text){
    var element = document.getElementById(id);
    element.value = text;
}

function getElementValue(id){
    var element = document.getElementById(id);
    return element.value;
}

function displayContactNotFound()
{
    alert("Not found");
}

let informationArray = new Array(); // 여기 배열에 전부 저장

function doSave() { // save 버튼 누르면 이거 실행 (따로 조건 필요 없음 그냥 실행)

    // 입력 값 가져오기
    let inputName = document.getElementById("name").value;
    let inputAddress = document.getElementById("address").value;
    let inputPhone = document.getElementById("phone").value;

    /* 
       중복 확인, 이때 내가 직접 입력한 값은 아직 informationArray.length 여기에
       저장이 되지 않아서 for문에서 길이 넣을 때 반영이 안 된다
       기존에 있던 걸로만 생각해서 실행한다
    */

    // 중복 체크 초기화
    let overlapCheck = false;
    for (let i = 0; i < informationArray.length; i++) {
        // inputName은 내가 입력한 거고, 왼쪽에 비교 값은 기존에 입력해서 저장된 값
        if (informationArray[i].doSaveName == inputName) {
            overlapCheck = true;
            break;
        }
    }

    // 중복이 false가 아니면 if 조건 충족
    if (!overlapCheck) {
        // 새연락처 객체 생성
        let doSaveInformation = {
            doSaveName: inputName,
            doSaveAddress: inputAddress,
            doSavePhone: inputPhone
        };
    
        // 배열 저장
        informationArray.push(doSaveInformation);
    
        // 메시지
        alert("Saves a contact in the store");
    } else {
        // 중복일 경우 메시지
        alert("Error! This name already exists!");
    }

    // 입력한 값 초기화, 이건 중복이어도 실행
    document.getElementById("name").value = "";
    document.getElementById("address").value = "";
    document.getElementById("phone").value = "";
}

    function doFind() { // find 버튼 누르면 이거 실행
        // 이름 인식 (Name 칸에 입력한 거 값이랑 비교)
        let doFindName = document.getElementById("name").value;
        // 버튼 누르면 다시 null로 초기화 해주기
        let findInformationArray = null;

        for (let i = 0; i < informationArray.length; i++) {
            if (doFindName == informationArray[i].doSaveName) {
                findInformationArray = informationArray[i];
                // 일치하는 거 있으면 for문 종료
                break;
            }
        }

        if (findInformationArray != null) { // 이러면 찾았다는 뜻 처음에 null 이었으니까
            // 처음은 html에 있는 id, 두 번째는 넣을 주소랑 폰 번호 값 각각
            // 해당 id의 칸에 해당 값을 넣는다
            displayElementValue("address", findInformationArray.doSaveAddress);
            displayElementValue("phone", findInformationArray.doSavePhone);
          } else {
            // 발견 못했을 때 메시지 (위에 함수 있음)
            displayContactNotFound();
          }
}