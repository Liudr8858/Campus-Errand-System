const BASE_URL = 'http://10.16.101.36:8080'

function request(options){

  const token = wx.getStorageSync('token')

  return new Promise((resolve, reject)=>{

    wx.request({
      url: BASE_URL + options.url,
      method: options.method || 'GET',
      data: options.data || {},
      header:{
        'token': token || '',
        'content-type': options.contentType || 'application/json'
      },

      success:(res)=>{

        console.log("全局请求返回：", res)

        // 🔥 统一处理401
        if(res.statusCode === 401){
          wx.removeStorageSync('token')

          wx.showToast({
            title:'请重新登录',
            icon:'none'
          })

          wx.reLaunch({
            url:'/pages/login/login'
          })

          return
        }

        resolve(res.data)
      },

      fail:(err)=>{
        wx.showToast({
          title:'网络错误',
          icon:'none'
        })
        reject(err)
      }

    })

  })
}

module.exports = {
  request
}