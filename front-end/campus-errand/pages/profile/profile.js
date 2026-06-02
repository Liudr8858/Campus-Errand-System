Page({

  data:{
    address:'',
    isEditing:false,
    user:{}
  },

  // 🔥 页面每次显示都会执行（比 onLoad 更适合）
  onShow(){

    const token = wx.getStorageSync('token')

    if(!token){
      wx.reLaunch({
        url: '/pages/login/login'
      })
      return
    }

    // 🔥 直接用token拿用户信息（不要再自己解析token了）
    wx.request({
      url:'http://10.16.101.36:8080/user/info',
      method:'GET',
      header:{
        'token':token
      },
      success:(res)=>{

        if(res.data.code==200){

          const user = res.data.data

          this.setData({
            user:user,
            address:user.address || ''
          })

        }
      }
    })

  },

  // 🔥 点击“修改”
  editAddress(){
    this.setData({
      isEditing:true
    })
  },

  // 🔥 输入地址
  getAddress(e){
    this.setData({
      address:e.detail.value
    })
  },

  // 🔥 保存地址
  saveAddress(){

    const token = wx.getStorageSync('token')

    if(!this.data.address){
      wx.showToast({
        title:'请输入地址',
        icon:'none'
      })
      return
    }

    wx.request({
      url:'http://10.16.101.36:8080/user/updateAddress',
      method:'POST',
      header:{
        'token':token,
        'content-type':'application/x-www-form-urlencoded'
      },
      data:{
        address:this.data.address
      },
      success:(res)=>{

        if(res.data.code==200){

          wx.showToast({
            title:'保存成功'
          })

          // 🔥 退出编辑模式
          this.setData({
            isEditing:false
          })

        }
      }
    })

  },

  logout(){
    wx.removeStorageSync('token')
    wx.reLaunch({
      url: '/pages/login/login'
    })
  }

})