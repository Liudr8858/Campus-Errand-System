const { request } = require('../../utils/request')

Page({

  data:{
    phone:'',
    name:'',
    password:''
  },

  getPhone(e){
    this.setData({ phone:e.detail.value })
  },

  getName(e){
    this.setData({ name:e.detail.value })
  },

  getPassword(e){
    this.setData({ password:e.detail.value })
  },

  register(){

    if(!this.data.phone || !this.data.name || !this.data.password){
      wx.showToast({
        title:'请填写完整信息',
        icon:'none'
      })
      return
    }

    request({
      url:'/user/register',
      method:'POST',
      data:{
        phone:this.data.phone,
        name:this.data.name,
        password:this.data.password
      }
    }).then(res=>{

      console.log("注册返回：",res)

      if(res.code == 200){
        wx.showToast({
          title:'注册成功'
        })

        wx.navigateBack() // 返回登录页
      }else{
        wx.showToast({
          title:res.msg || '注册失败',
          icon:'none'
        })
      }

    })

  }

})