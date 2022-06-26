import { apiInstance } from "./index.js";

const api = apiInstance();

async function listNotice(param, success, fail) {
  await api.get(`/notice`, { params: param }).then(success).catch(fail);
}

function writeNotice(notice, success, fail) {
  api.post(`/notice`, JSON.stringify(notice)).then(success).catch(fail);
}

function getNotice(nid, success, fail) {
  api.get(`/notice/${nid}`).then(success).catch(fail);
}

async function modifyNotice(nid, notice, success, fail) {
  await api
    .put(`/notice/${nid}`, JSON.stringify(notice))
    .then(success)
    .catch(fail);
}

async function deleteNotice(nid, success, fail) {
  await api.delete(`/notice/${nid}`).then(success).catch(fail);
}

export { listNotice, writeNotice, getNotice, modifyNotice, deleteNotice };
