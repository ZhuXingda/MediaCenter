import axios from "axios";
//默认请求超时时间
axios.defaults.timeout = 10000;
//post请求请求头
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8';
axios.defaults.withCredentials = true

//封装get请求
export function getRequest(url, params){
  return axios.get(url, {
    params: params
  })
}

export function putRequest(url, params){
  return axios.put(url,params,{
    headers: {
      'Content-Type': 'application/json'
    }
  })
}

export function postRequest(url, body) {
  return axios.post(url, JSON.stringify(body),{
    headers: {
      'Content-Type': 'application/json'
    }
  })
}

export function getQuery(key){
  let pairs = window.location.search.substr(1).split('&');
  for (let i=0;i<pairs.length;i++) {
    let pair = pairs[i].split("=");
    if(pair[0] === key){return pair[1];}
  }
  return false;
}
