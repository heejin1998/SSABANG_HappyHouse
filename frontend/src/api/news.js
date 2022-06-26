import { newsInstance } from "./index.js";

const news = newsInstance();

function newsList(success, fail) {
    news.get("/news").then(success).catch(fail);
}

export { newsList };
