Page({

  data:{
    title:'',
    description:'',
    price:'',
    address:''
  },

  getTitle(e){
    this.setData({
      title:e.detail.value
    })
  },

  getDesc(e){
    this.setData({
      description:e.detail.value
    })
  },

  getPrice(e){
    this.setData({
      price:e.detail.value
    })
  },
  onShow(){

    const token = wx.getStorageSync('token')
  
    if(!token){
      wx.reLaunch({
        url:'/pages/login/login'
      })
      return
    }
  
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
            address:user.address || ''
          })
  
        }
      }
    })
  
  },
  publish(){

    const token = wx.getStorageSync('token')

    if(!this.data.title || !this.data.description || !this.data.price || !this.data.address){
      wx.showToast({
        title:'请填写完整信息',
        icon:'none'
      })
      return
    }

    wx.request({
      url:'http://10.16.101.36:8080/task/publish',
      method:'POST',
      header:{
        'token':token,
        'content-type':'application/json'
      },
      data:{
        title:this.data.title,
        description:this.data.description,
        price:this.data.price,
        address:this.data.address
      },
      success:(res)=>{
        console.log(res)

        if(res.data.code==200){
          wx.showToast({
            title:'发布成功'
          })

          // 🔥 返回首页并刷新
          wx.switchTab({
            url:'/pages/home/home'
          })
        }
      }
    })

  }

})