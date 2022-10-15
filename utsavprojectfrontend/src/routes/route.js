import request from 'superagent';

const backendRoute = "http://localhost:1111";

const requests = {
    post : (url, body) =>
            request
                .post(url , body)
                .withCredentials(true),
    put : (url, body) =>
            request
                .put(url , body)
                .withCredentials(true),
    get : (url) =>
            request
                .get(url )
                ,
    delete : (url) =>
            request
                .del(url )
                .withCredentials(true),
}

const Search = {
    productSearch : (productName) => requests.get(backendRoute+'/getProducts/'+productName)
};

export default {Search};