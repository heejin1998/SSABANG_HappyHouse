import { apiInstance } from "./index.js";

const api = apiInstance();

async function registerUser(user, success, fail) {
  await api.post(`/user`, JSON.stringify(user)).then(success).catch(fail);
}

async function getToken(code, success, fail) {
  await api.get(`/klogin?authorize_code=${code}`).then(success).catch(fail);
}

async function isUser(kid, success, fail) {
  await api.get(`/klogin/check/${kid}`).then(success).catch(fail);
}

function checkUsername(username, success, fail) {
  api.get(`/klogin/duplicate/${username}`).then(success).catch(fail);
}

function listUser(success, fail) {
  api.get(`/user`).then(success).catch(fail);
}

async function getUser(kid, success, fail) {
  await api.get(`/user/${kid}`).then(success).catch(fail);
}

function deleteUser(kid, success, fail) {
  api.delete(`/user/${kid}`).then(success).catch(fail);
}

async function updateUser(kid, user, success, fail) {
  await api.put(`/user/${kid}`, JSON.stringify(user)).then(success).catch(fail);
}

function listFavor(kid, success, fail) {
  api.get(`/user/${kid}/favor`).then(success).catch(fail);
}

function deleteFavor(kid, aptCode, success, fail) {
  api.delete(`/user/${kid}/favor/${aptCode}`).then(success).catch(fail);
}

async function insertFavor(kid, aptCode, success, fail) {
  await api.post(`/user/${kid}/favor/${aptCode}`).then(success).catch(fail);
}
export {
  registerUser,
  getToken,
  isUser,
  checkUsername,
  listUser,
  getUser,
  deleteUser,
  updateUser,
  listFavor,
  deleteFavor,
  insertFavor,
};
