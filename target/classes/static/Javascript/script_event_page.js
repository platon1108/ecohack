
document.addEventListener('DOMContentLoaded', function() {
    var myMap, myPlacemark;
    function init() {
        var adres = document.getElementById('address').value
        console.log(adres)
        adres=adres.split('_')
        myMap = new ymaps.Map("map", {
            center: [adres[0],adres[1]], //Координаты куда карта показывает при переходе
            zoom: 16
        });
        myPlacemark = new ymaps.Placemark([adres[0],adres[1]], { //Координаты куда метка ставиться
            hintContent: 'Москва', //Инфа при наведении на метку
            balloonContent: '...' //инфа при нажатии
        });
        myMap.geoObjects.add(myPlacemark);
    }
    function showMap() {
        document.getElementById('map').style.display = 'block';
    }
    ymaps.ready(init)
    showMap()
});