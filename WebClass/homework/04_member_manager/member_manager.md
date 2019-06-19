# 회원 관리

## 자바스크립트 버전 코드 링크

링크 : https://github.com/magiccircle03/bit201904java/blob/master/WebClass/homework/04_member_manager/member_manager_js.html

## 제이쿼리 활용 버전 코드 링크

링크 : https://github.com/magiccircle03/bit201904java/blob/master/WebClass/homework/04_member_manager/member_manager_jQuery.html

## ==============================================

## 자바스크립트 버전 코드

```html
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>회원관리</title>
    <style>
        * {
            margin: 0;
            padding: 0;
        }
        body {
        /*    background-image: url(../images/bg/city.jpg);*/
            background-color: black;
            background-repeat: no-repeat;
            background-size: cover;
        }
        input {
            background-color: rgba(255, 255, 255, 0.6);
            border: 1px solid #DDD;
        }
        
        h1{
            text-shadow: 2px 2px #999;
        }
        
        #main_wrap {
            /*정확히 500px하려고 보더랑 패딩 생각했음*/
            width: 478px;
            /*확인용으로 height줘본거*/
            /*height: 200px;*/
            /*상하 0 좌우 auto*/
            margin: 0 auto;
            margin-top: 30px;
            padding: 10px;
            /*            background-color: rgba(255,255,255,0.8);
            border: 3px solid #333;
            box-shadow: 5px 5px rgba(0,0,0,0.7);
            border-radius: 5px;*/
        }
        .box_deco {
            background-color: rgba(255, 255, 255, 0.7);
            /*            background-color:rgba(0,0,0,0.5);
            color: white;*/
            /*            border: 3px solid #333;*/
            box-shadow: 5px 5px rgba(0, 0, 0, 0.5);
            border-radius: 5px;
        }
        .title {
            font-size: 24px;
        }
        hr {
            border: 0;
            border-top: 1px solid #AAA;
            margin: 10px 0;
        }
        #regBox>form>table {
            width: 100%;
        }
        #regBox>form>table td {
            width: 25%;
            padding: 5px;
        }
        .input {
            width: 100%;
            font-size: 16px;
            padding: 3px;
            display: block;
            margin-top: 5px;
        }
        input[type=submit],
        input[type=button] {
            background-color: cornflowerblue;
            color: aliceblue;
            border: 1px solid #DDD;
            font-size: 16px;
            padding: 5px 0;
        }
        label {
            display: block;
        }
        .table {
            width: 100%;
            border-collapse: collapse;
            margin: 15px 0;
        }
        .table td {
            padding: 5px;
            text-align: center;
            border: 1px solid #DDD;
            background-color: rgba(255, 255, 255, 0.6);
        }
/*        #memberList .table tr:nth-child(1) {
            background-color: rgba(51, 73, 185, 0.5);
        }*/
        a {
            cursor: pointer;
        }
        #editBox {
            background-color: rgba(255, 255, 255, 0.8);
            width: 300px;
            padding: 10px;
            position: absolute;
            top: 100px;
            left: 50%;
            margin-left: -150px;
            display: none;
        }
    </style>
</head>

<body>
    <div id="main_wrap" class="box_deco">
        <h1 class="title">MEMBER MANAGER</h1>
        <hr>
        <div id="regBox">
            <h3>회원 등록</h3>
            <!--데이터 입력 영역 시작-->

            <!--액션 생략하면 get방식으로 자기자신에게 온다-->
            <form id="regForm" type="post">
                <table>
                    <tr>
                        <!--네임속성이 넘어간댔음-->
                        <td>
                            <input type="text" id="userId" name="userId" class="input" placeholder="아이디(이메일)"></td>
                        <td>
                            <input type="password" id="userPw" name="userPw" class="input" placeholder="비밀번호"></td>
                        <td>
                            <input type="text" id="userName" name="userName" class="input" placeholder="이름"></td>
                        <td>
                            <input type="submit" value="등록" class="input">
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <!--데이터 입력 영역 끝-->


        <hr>
        <!--회원리스트 테이블 시작-->
        <h3>회원 리스트</h3>
        <div id="memberList">
            <table class="table">
                <tbody id="listRow">
                    <tr>
                        <td>순번(index)</td>
                        <td>아이디</td>
                        <td>비밀번호</td>
                        <td>이름</td>
                        <td>관리</td>
                    </tr>

                    <!--<tr>
                            <td>7</td>
                            <td>test</td>
                            <td>1234</td>
                            <td>테스터</td>
                            <td><a href="" onclick="editMember(7)">수정</a>
                            <a href="" onclick="delMember(7)">삭제</a></td>
                        </tr>-->
                    <!--이런식으로 들어갈것-->
                    <!--a에 함수넣어도 좋고 온클릭처리도 되고-->

                </tbody>

            </table>

        </div>
        <!--회원리스트 테이블 끝-->

        <!--회원정보 수정폼 시작-->
        <div id="editBox" class="box_deco">
            <h3>회원정보 수정</h3>
            <form id="editForm" method="post">
                <table class="table">
                    <tr>
                        <td>아이디</td>
                        <td><input type="text" id="editId" class="input"></td>
                    </tr>
                    <tr>
                        <td>비밀번호</td>
                        <td><input type="password" id="editPw" class="input"></td>
                    </tr>
                    <tr>
                        <td>이름</td>
                        <td><input type="text" id="editName" class="input">

                            <input type="hidden" id="editIndex"></td>
                    </tr>
                    <tr>
                        <td colspan="2"><input type="submit" value="수정하기"><input id="cancel" type="button" value="취소"></td>
                    </tr>
                </table>
            </form>
        </div>
        <!--회원정보 수정폼 끝-->

    </div>
    <script>
        //Member 객체들을 저장하기 위한 배열 생성
        //var members = [];
        // 저장소 변수 선언
        // localStorage 에서 데이터 유무에 따라 배열선언
        var members;
        //alert(localStorage.getItem('members'));
        // 회원데이터 저장 목적으로 하는 생성자함수 정의
        // id : 회원의 아이디, 이메일주소
        // pw : 회원의 비밀번호
        // name : 회원의 이름
        // 메서드 : 사용자의 데이터를 가지고 html텍스트를 완성하는 메서드 생성
        //생성자함수. 이름 대문자로 시작
        function Member(id, pw, name) {
            this.userId = id;
            this.userPw = pw;
            this.userName = name;
        }
        Member.prototype.makeHtml = function(i) {
            var nHtml = '';
            nHtml += '<tr>\n';
            nHtml += '<td>' + i + '</td>\n'
            nHtml += '<td>' + this.userId + '</td>\n'
            nHtml += '<td>' + this.userPw + '</td>\n'
            nHtml += '<td>' + this.userName + '</td>\n'
            nHtml += '<td><a onclick="editMember(' + i + ')">수정</a> | <a onclick="delMember(' + i + ')">삭제</a></td>\n';
            nHtml += '</tr>\n';
            return nHtml;
        };
        Member.prototype.showData = function() {
            alert('아이디 : ' + this.userId + '\n 비밀번호 : ' + this.userPw + '\n 이름 : ' + this.userName);
        };
        /*        Member.prototype.toString = function() {
                    var str = '<h4>아이디 : ' + this.userId + '비밀번호 : ' + this.userPw + '이름 : ' + this.userName + ' </h4>\n';
                    return str;
                };*/
        window.onload = function() {
            //getItem하면 null혹은 String이 나올 것. (null은 펄스였음)
            if (localStorage.getItem('members') == null) {
                // 최초 실행
                members = [];
                //localStorage에 배열 저장
                //스트링으로 변환해서 넣어줌
                localStorage.setItem('members', JSON.stringify(members));
            } else {
                //localStorage에 members가 존재하는 경우
                //객체로 변환해줌
                members = JSON.parse(localStorage.getItem('members'));
                setListTable();
            }
            //regForm 문서객체 만들기
            var regForm = document.getElementById('regForm');
            // 회원 입력 폼의 submit 재정의
            regForm.onsubmit = function() {
                //사용자 입력데이터 input 캐스팅
                var uId = document.getElementById('userId');
                var uPw = document.getElementById('userPw');
                var uName = document.getElementById('userName');
                if (uId.value.length < 1) {
                    alert('아이디를 입력해주세요!');
                    uId.focus();
                    return false;
                }
                if (uPw.value.length < 1) {
                    alert('비밀번호를 입력해주세요!');
                    uPw.focus();
                    return false;
                }
                if (uName.value.length < 1) {
                    alert('이름을 입력해주세요!');
                    uName.focus();
                    return false;
                }
                //새로운 데이터로 객체 생성
                var newMember = new Member(uId.value, uPw.value, uName.value);
                //newMember.showData();
                members.push(newMember); //배열 요소 추가 -> 변경이 일어남
                localStorage.setItem('members', JSON.stringify(members));
                alert('정상적으로 입력되었습니다!');
                setListTable();
                regForm.reset();
                uId.focus();
                //전송할 것이 아니기 때문에..! 정상으로 들어오면 객체만들어줄거임
                return false;
            };
            // 회원 수정 폼의 submit 재정의
            var editForm = document.getElementById('editForm');
            editForm.onsubmit = function() {
                //공백을 체크하는 유효성 검사
                //1. 캐스팅
                //2. 캐스팅된 객체.value 값을 검사 : 문자열의 길이 검사
                var editId = document.getElementById('editId');
                var editPw = document.getElementById('editPw');
                var editName = document.getElementById('editName');
                var editIndex = document.getElementById('editIndex');
                if (editId.value.length < 1) {
                    alert('아이디를 입력해주세요!');
                    editId.focus();
                    return false;
                }
                if (editPw.value.length < 1) {
                    alert('비밀번호를 입력해주세요!');
                    editPw.focus();
                    return false;
                }
                if (editName.value.length < 1) {
                    alert('이름을 입력해주세요!');
                    editName.focus();
                    return false;
                }
                //alert(editIndex.value);
                //아이디 수정
                members[editIndex.value].userId = editId.value;
                //비밀번호 수정
                members[editIndex.value].userPw = editPw.value;
                //이름 수정
                members[editIndex.value].userName = editName.value;
                //alert(members[editIndex.value]);
                localStorage.setItem('members', JSON.stringify(members));
                alert('수정되었습니다!');
                setListTable();
                editForm.reset();
                document.getElementById('editBox').style.display = 'none';
                return false;
            };
            var cBtn = document.getElementById('cancel');
            cBtn.onclick = function() {
                document.getElementById('editBox').style.display = 'none';
            };
        };
        /*        //members  배열의 요소들을 출력기능. 입력되든 수정되든 삭제되든 얘 보여주면 되겠음
                function setListTable() {
                    var newHtml = '';
                    
                    for (var i = 0; i < members.length; i++) {
                        newHtml += members[i].toString();
                    }
                    //새롭게 그리면 사용자입장에선 바뀐걸로 보임.
                    document.getElementById('memberList').innerHTML = newHtml;
                }*/
        function setListTable() {
            var newHtml = '<tr>\n<td>순번(index)</td>\n<td>아이디</td>\n<td>비밀번호</td>\n<td>이름</td>\n<td>관리</td>\n</tr>\n';
            for (var i = 0; i < members.length; i++) {
                //newHtml += members[i].makeHtml(i);
                newHtml += '<tr>\n';
                newHtml += '<td>' + i + '</td>\n';
                newHtml += '<td>' + members[i].userId + '</td>\n';
                newHtml += '<td>' + members[i].userPw + '</td>\n';
                newHtml += '<td>' + members[i].userName + '</td>\n';
                newHtml += '<td><a onclick="editMember(' + i + ')">수정</a> | <a onclick="delMember(' + i + ')">삭제</a></td>\n';
                newHtml += '</tr>\n';
            }
            //alert(newHtml);
            //새롭게 그리면 사용자입장에선 바뀐걸로 보임.
            document.getElementById('listRow').innerHTML = newHtml;
        }
        function editMember(index) {
            //var eBox = document.getElementById('editBox');
            //eBox.style.display = 'block';
            document.getElementById('editBox').style.display = 'block';
            alert(index + '번 요소 수정');
            //수정폼의 아이디,비밀번호,이름 input 캐스팅
            var eId = document.getElementById('editId');
            var ePw = document.getElementById('editPw');
            var eName = document.getElementById('editName');
            var eIndex = document.getElementById('editIndex');
            //기존 데이터를 캐스팅한 객체 value에 대입
            eId.value = members[index].userId;
            ePw.value = members[index].userPw;
            eName.value = members[index].userName;
            eIndex.value = index;
            //수정 폼 만들기
        }
        function delMember(index) {
            //alert(index + '번 요소 삭제');
            var delChk = confirm('삭제하시겠습니까?');
            if (delChk) {
                //삭제 -> 배열에서 요소 삭제
                members.splice(index, 1);
                localStorage.setItem('members', JSON.stringify(members));
                alert(index + '번 삭제되었습니다!');
                setListTable();
            }
        }
    </script>
</body>

</html>
```


## 제이쿼리 활용 버전 코드
```html

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>회원관리</title>
    <link rel="stylesheet" type="text/css" href="css/member_manager.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
</head>

<body>
    <div id="main_wrap" class="box_deco">
        <h1 class="title">MEMBER MANAGER</h1>
        <hr>
        <div id="regBox">
            <h3>회원 등록</h3>

            <!--데이터 입력 영역 시작-->
            <form id="regForm" type="post">
                <table>
                    <tr>
                        <td>
                            <input type="text" id="userId" name="userId" class="input" placeholder="아이디(이메일)"></td>
                        <td>
                            <input type="password" id="userPw" name="userPw" class="input" placeholder="비밀번호"></td>
                        <td>
                            <input type="text" id="userName" name="userName" class="input" placeholder="이름"></td>
                        <td>
                            <input type="submit" value="등록" class="input">
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <!--데이터 입력 영역 끝-->


        <hr>
        <!--회원리스트 테이블 시작-->
        <h3>회원 리스트</h3>
        <div id="memberList">
            <table class="table">
                <tbody id="listRow">
                </tbody>
            </table>
        </div>
        <!--회원리스트 테이블 끝-->

        <!--회원정보 수정폼 시작-->
        <div id="editBox" class="box_deco">
            <h3>회원정보 수정</h3>
            <form id="editForm" method="post">
                <table class="table">
                    <tr>
                        <td>아이디</td>
                        <td><input type="text" id="editId" class="input"></td>
                    </tr>
                    <tr>
                        <td>비밀번호</td>
                        <td><input type="password" id="editPw" class="input"></td>
                    </tr>
                    <tr>
                        <td>이름</td>
                        <td><input type="text" id="editName" class="input">

                            <input type="hidden" id="editIndex"></td>
                    </tr>
                    <tr>
                        <td colspan="2"><input type="submit" value="수정하기"><input id="cancel" type="button" value="취소"></td>
                    </tr>
                </table>
            </form>
        </div>
        <!--회원정보 수정폼 끝-->

    </div>
    <script>
        //Member 객체들을 저장하기 위한 배열 생성
        //var members = [];
        // 저장소 변수 선언
        // localStorage 에서 데이터 유무에 따라 배열선언
        var members;
        //alert(localStorage.getItem('members'));
        // 회원데이터 저장 목적으로 하는 생성자함수 정의
        // id : 회원의 아이디, 이메일주소
        // pw : 회원의 비밀번호
        // name : 회원의 이름
        // 메서드 : 사용자의 데이터를 가지고 html텍스트를 완성하는 메서드 생성
        //생성자함수. 이름 대문자로 시작
        function Member(id, pw, name) {
            this.userId = id;
            this.userPw = pw;
            this.userName = name;
        }
        //에러 수정 : 레디 안에 있으니 인식을 못 했다
        function editMember(index) {
            $('#editBox').css('display', 'block');
            alert(index + '번 요소를 수정합니다!');
            $('#editId').val(members[index].userId);
            $('#editPw').val(members[index].userPw);
            $('#editName').val(members[index].userName);
            $('#editIndex').val(index);
        }
        function setListTable() {
            var newHtml = '<tr>\n<td>순번(index)</td>\n<td>아이디</td>\n<td>비밀번호</td>\n<td>이름</td>\n<td>관리</td>\n</tr>\n';
            $.each(members, function(index, item) {
                newHtml += '<tr>\n';
                newHtml += '<td>' + index + '</td>\n';
                newHtml += '<td>' + item.userId + '</td>\n';
                newHtml += '<td>' + item.userPw + '</td>\n';
                newHtml += '<td>' + item.userName + '</td>\n';
                newHtml += '<td><a onclick="editMember(' + index + ')">수정</a> | <a onclick="delMember(' + index + ')">삭제</a></td>\n';
                newHtml += '</tr>\n';
            });
            $('#listRow').html(newHtml);
        }
        function delMember(index) {
            //alert(index + '번 요소 삭제');
            var delChk = confirm('삭제하시겠습니까?');
            if (delChk) {
                //삭제 -> 배열에서 요소 삭제
                members.splice(index, 1);
                localStorage.setItem('members', JSON.stringify(members));
                alert(index + '번 삭제되었습니다!');
                setListTable();
            }
        }
        $(document).ready(function() {
            if (localStorage.getItem('members') == null) {
                // 최초 실행
                members = [];
                //localStorage에 배열 저장
                //스트링으로 변환해서 넣어줌
                localStorage.setItem('members', JSON.stringify(members));
            } else {
                //localStorage에 members가 존재하는 경우
                //객체로 변환해줌
                members = JSON.parse(localStorage.getItem('members'));
                setListTable();
            }
            //regForm submit시
            $('#regForm').submit(function() {
                var uId = $('#userId').val();
                var uPw = $('#userPw').val();
                var uName = $('#userName').val();
                if (uId.length < 1) {
                    alert('아이디를 입력해주세요');
                    $('#userId').focus();
                    return false;
                }
                if (uPw.length < 1) {
                    alert('비밀번호를 입력해주세요');
                    $('#userPw').focus();
                    return false;
                }
                if (uName.length < 1) {
                    alert('이름을 입력해주세요');
                    $('#userName').focus();
                    return false;
                }
                //새로운 데이터로 객체 생성
                var newMember = new Member(uId, uPw, uName);
                members.push(newMember); //배열 요소 추가 -> 변경이 일어남
                localStorage.setItem('members', JSON.stringify(members));
                alert('정상적으로 입력되었습니다!');
                setListTable();
                regForm.reset();
                $('#userId').focus();
                return false;
            });
            $('#editForm').submit(function() {
                var eId = $('#editId').val();
                var ePw = $('#editPw').val();
                var eName = $('#editName').val();
                var eIndex = $('#editIndex').val();
                if (eId.length < 1) {
                    alert('아이디를 입력해주세요!');
                    $('#editId').focus();
                    return false;
                }
                if (ePw.length < 1) {
                    alert('비밀번호를 입력해주세요!');
                    $('#editPw').focus();
                    return false;
                }
                if (eName.length < 1) {
                    alert('이름을 입력해주세요!');
                    $('#editName').focus();
                    return false;
                }
                members[eIndex].userId = eId;
                members[eIndex].userPw = ePw;
                members[eIndex].userName = eName;
                localStorage.setItem('members', JSON.stringify(members));
                alert('수정되었습니다!');
                setListTable();
                editForm.reset();
                $('#editBox').css('display', 'none');
                return false;
            });
            $('#cancel').click(function() {
                $('#editBox').css('display', 'none');
            });
        });
    </script>
</body>

</html>
```
