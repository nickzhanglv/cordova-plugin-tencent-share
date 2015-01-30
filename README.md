# cordova-plugin-tencent-share

share to qq friend
TencentShare.qqShare({
    "appid": "xxxxxxx",
    "title": "title",
    "summary": "summary",
    "image_url": "http://img3.cache.netease.com/photo/0005/2013-03-07/8PBKS8G400BV0005.jpg",
    "target_url": "http://weibo.com/u/5398990359"
}, function(e) {
    alert("ok " + e)
}, function(e) {
    alert("error " + e)
});

share to qzone 
TencentShare.qzoneShare({
    "appid": "xxxxxxx",
    "title": "title",
    "summary": "summary",
    "image_url": "http://img3.cache.netease.com/photo/0005/2013-03-07/8PBKS8G400BV0005.jpg",
    "target_url": "http://weibo.com/u/5398990359"
}, function(e) {
    alert("ok " + e)
}, function(e) {
    alert("error " + e)
});
