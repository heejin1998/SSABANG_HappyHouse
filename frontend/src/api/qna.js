import { apiInstance } from "./index.js";

const api = apiInstance();

async function listQna(param, success, fail) {
  await api.get(`/qna`, { params: param }).then(success).catch(fail);
}

function writeQna(qna, success, fail) {
  api.post(`/qna`, JSON.stringify(qna)).then(success).catch(fail);
}

async function getQna(qid, success, fail) {
  await api.get(`/qna/${qid}`).then(success).catch(fail);
}

async function modifyQna(qid, qna, success, fail) {
  await api.put(`/qna/${qid}`, JSON.stringify(qna)).then(success).catch(fail);
}

function deleteQna(qid, success, fail) {
  api.delete(`/qna/${qid}`).then(success).catch(fail);
}

async function getQnaReply(qid, success, fail) {
  await api.get(`qna/reply/${qid}`).then(success).catch(fail);
}

async function writeQnaReply(qid, qnaReply, success, fail) {
  await api.post(`qna/reply/${qid}`, qnaReply).then(success).catch(fail);
}
export {
  listQna,
  writeQna,
  getQna,
  modifyQna,
  deleteQna,
  getQnaReply,
  writeQnaReply,
};
