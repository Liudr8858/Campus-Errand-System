const { request } = require('../../utils/request')

Page({

  data:{
    taskList:[]
  },

  onShow(){

    const token = wx.getStorageSync('token')
    console.log("当前token：",token)

    if(!token){
      wx.reLaunch({
        url:'/pages/login/login'
      })
      return
    }

    // 🔥 用封装后的请求
    request({
      url:'/task/list'
    }).then(res=>{

      if(res.code == 200){
        this.setData({
          taskList: res.data || []
        })
      }else{
        wx.showToast({
          title:'加载失败',
          icon:'none'
        })
      }

    })

  },

  goDetail(e){
    const id = e.currentTarget.dataset.id

    wx.navigateTo({
      url:'/pages/taskdetail/taskdetail?id=' + id
    })
  },

  acceptTask(e){

    const id = e.currentTarget.dataset.id

    request({
      url:'/task/accept',
      method:'POST',
      data:{ id },
      contentType:'application/x-www-form-urlencoded'
    }).then(res=>{

      if(res.code == 200){
        wx.showToast({
          title:'接单成功'
        })

        this.onShow()
      }else{
        wx.showToast({
          title: res.msg || '失败',
          icon:'none'
        })
      }

    })

  }

})