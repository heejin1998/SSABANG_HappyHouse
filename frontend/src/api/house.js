import { apiInstance } from "./index.js";

const api = apiInstance();

function sidoList(success, fail) {
    api.get(`/map/sido`).then(success).catch(fail);
}

function gugunList(params, success, fail) {
    api.get(`/map/gugun`, { params: params }).then(success).catch(fail);
}

function dongList(params, success, fail) {
    api.get(`/map/dong`, { params: params }).then(success).catch(fail);
}

function houseList(params, success, fail) {
    api.get(`/map/apt`, { params: params }).then(success).catch(fail);
}

function houseListGeo(params, success, fail) {
    api.get(`/map/aptgeo`, { params: params }).then(success).catch(fail);
}

function houseListGeoWithDeals(params, success, fail) {
    api.get(`/map/aptgeo/deals`, { params: params }).then(success).catch(fail);
}

function houseInfo(aptCode, success, fail) {
    api.get(`map/apt/${aptCode}`).then(success).catch(fail);
}

function dongCode(dongCode, success, fail) {
    api.get(`map/dong/${dongCode}`).then(success).catch(fail);
}

function distance({ category, aptCode }, success, fail) {
    api.get(`map/distance/${category}/${aptCode}`).then(success).catch(fail);
}

function houseInfoByName({ aptName }, success, fail) {
    api.get(`map/apt/name/${aptName}`).then(success).catch(fail);
}

function addressByDongCode(dongCode, success, fail) {
    api.get(`map/geo/${dongCode}`).then(success).catch(fail);
}

export { sidoList, gugunList, houseList, dongList, houseListGeo, houseListGeoWithDeals, houseInfo, dongCode, distance, houseInfoByName, addressByDongCode };
