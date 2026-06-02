Page({

  data:{
    phone:'',
    password:''
  },
  onLoad(){
    const token = wx.getStorageSync('token')

    if(token){
      wx.switchTab({
        url: '/pages/home/home'
      })
    }
  },

  getPhone(e){
    this.setData({
      phone:e.detail.value
    })
  },

  getPassword(e){
    this.setData({
      password:e.detail.value
    })
  },
  goRegister(){
    wx.navigateTo({
      url:'/pages/register/register'
    })
  },
  

  login(){

    let that = this

    wx.request({
      url: 'http://10.16.101.36:8080/user/login',
      method: 'POST',
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      data: {
        phone: this.data.phone,
        password: this.data.password
      },
      success: (res) => {
        console.log("返回结果：", res)
      
        if(res.data.code == 200){
      
          // 存 token
          wx.setStorageSync('token', res.data.data)
      
          // 跳转首页
          wx.switchTab({
            url: '/pages/home/home'
          })
      
        }else{
          wx.showToast({
            title: '登录失败',
            icon: 'none'
          })
        }
      },
      fail: (err) => {
        console.log("请求失败：", err)
      }
    })

  }

})