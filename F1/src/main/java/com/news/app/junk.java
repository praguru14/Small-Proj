package com.news.app;

public class junk {

//
//    @GetMapping("/pg")
//    public ResponseEntity<String> list() throws UnsupportedEncodingException {
//
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
//        ResponseEntity<String> res = restTemplate.exchange(API_URL,
//                HttpMethod.GET,entity, String.class);
//        HttpStatus statusCode = (HttpStatus) res.getStatusCode();
//        String responseBody = res.getBody();
//        String jsonResponse = "{\"statusCode\":" + statusCode.value() + ", \"responseBody\":\"" + responseBody.replace("\"", "\\\"") + "\"}";
//       httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//       return new ResponseEntity<>(jsonResponse,httpHeaders,HttpStatus.OK);
//        // String jsonRes = "{\"statusCode":"+statusCode.value() + ",\"responseBody\":\""+responseBody+"\"}";
////        HttpStatus statusCode = (HttpStatus) res.getStatusCode();
////        String encodedBody = URLEncoder.encode(String.valueOf(res), "UTF-8");
////
////        // Accessing response body
////        String responseBody = res.getBody();
////        System.out.println(ResponseEntity.status(statusCode).body(responseBody));
////        return ResponseEntity.status(statusCode).body(responseBody);
//       // return "0";
//    }
//    @GetMapping("/p")
//    public ResponseEntity<String> list1(){
//
//     ResponseEntity<String> res = restTemplate.getForEntity(API_URL, String.class);
//     return res;
////        HttpHeaders httpHeaders = new HttpHeaders();
////        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
////        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
////        return restTemplate.exchange(API_URL, HttpMethod.GET,entity, String.class).getBody();
//
//        // return "0";
//    }
}
