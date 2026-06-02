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
      url:'http://10.16.101.36:8080/task/myAccept',
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
  },

  // 🔥 完成任务（必须写在外面！）
  finishTask(e){

    const id = e.currentTarget.dataset.id
    const token = wx.getStorageSync('token')

    wx.request({
      url:'http://10.16.101.36:8080/task/finish',
      method:'POST',
      header:{
        'token':token,
        'content-type':'application/x-www-form-urlencoded'
      },
      data:{
        id:id
      },
      success:(res)=>{
        console.log(res)

        if(res.data.code==200){
          wx.showToast({
            title:'已完成'
          })

          // 🔥 刷新页面
          this.onShow()
        }
      }
    })
  }

})