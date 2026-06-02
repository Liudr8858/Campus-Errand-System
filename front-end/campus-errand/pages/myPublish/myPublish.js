Page({

  data:{
    taskList:[]
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
      url:'http://10.16.101.36:8080/task/myPublish',
      method:'GET',
      header:{
        'token':token
      },
      success:(res)=>{
        console.log(res)

        if(res.data.code==200){
          this.setData({
            taskList:res.data.data
          })
        }
      }
    })

  }

})