<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>

<script>

    new daum.Postcode({

        oncomplete: function(data) {

            if(data.userSelectedType=="R"){

                // userSelectedType : 검색 결과에서 사용자가 선택한 주소의 타입

                // return type : R - roadAddress, J : jibunAddress

                // TestApp 은 안드로이드에서 등록한 이름

                window.TestApp.setAddress(data.zonecode, data.roadAddress, data.buildingName, data.sigungu , data.latitude, data.longitude);

            }

            else{

                window.TestApp.setAddress(data.zonecode, data.jibunAddress, data.buildingName, data.sigungu, data.latitude, data.longitude);

            }

        }

    }).open();

</script>
