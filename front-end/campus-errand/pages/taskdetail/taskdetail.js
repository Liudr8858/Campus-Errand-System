Page({

  data:{
    task:{},
    id:null
  },

  onLoad(options){

    const id = options.id

    this.setData({
      id:id
    })

    this.loadDetail()   // 🔥 统一调用
  },

  // 🔥 单独封装：获取详情
  loadDetail(){

    const token = wx.getStorageSync('token')

    wx.request({
      url:'http://10.16.101.36:8080/task/detail',
      method:'GET',
      header:{
        'token':token
      },
      data:{
        id:this.data.id
      },
      success:(res)=>{
        console.log("详情刷新：",res)

        if(res.data.code==200){
          this.setData({
            task:res.data.data
          })
        }
      }
    })
  },

  // 🔥 接单
  acceptTask(){

    const token = wx.getStorageSync('token')

    wx.request({
      url:'http://10.16.101.36:8080/task/accept',
      method:'POST',
      header:{
        'token':token,
        'content-type':'application/x-www-form-urlencoded'
      },
      data:{
        id:this.data.id
      },
      success:(res)=>{
        console.log(res)

        if(res.data.code==200){
          wx.showToast({
            title:'接单成功'
          })

          // 🔥 正确刷新方式
          this.loadDetail()
        }
      }
    })
  }

})