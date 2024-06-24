<template>
  <el-container>
    <el-header>
      <h3>智能会议管理系统</h3>
      <div class="d-f j-c-s a-i-c">
        <el-menu
           class="el-menu-demo"
           mode="horizontal"
           @select="handleSelect"
           background-color="#32d01a" text-color="#fff" active-text-color="#FFD700"
        >
          <el-menu-item index="/home/meeting" style="font-size: 1.1vw">会议管理</el-menu-item>
          <el-menu-item v-if="$currentUser.role==='管理员'" index="/home/analyse" style="font-size: 1.1vw">智能分析</el-menu-item>
          <el-menu-item index="/home/user" style="font-size: 1.1vw">用户中心</el-menu-item>
        </el-menu>
        <div class="d-f a-i-c" style="margin-left: 50px;">
          <i class="el-icon-s-custom"></i>
          <span style="font-size: small;margin-left: 5px;">{{$currentUser.realname}} ({{$currentUser.role}})</span>
          <a href="javascript:void(0)" style="margin-left: 10px;" @click="logout"><i class="iconfont icon-tuichu"></i></a>
        </div>
      </div>
    </el-header>
    <el-container>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>

export default {
  name: 'Home',
  created() {
    document.title = '首页'
  },
  methods:{
    handleSelect:function (index){
      this.$router.push(index)
    },
    logout:function (){
      this.$confirm('确定退出系统?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$router.push('/')
      }).catch(() => {
      });
    }
  }
}
</script>
<style scoped>
a{
  display: inline-block;
  text-decoration: none;
  color: #fff;
}
.icon-tuichu:before {
  content: "\e606";
}
.el-header {
  background: #32d01a;
  color: #fff;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.el-aside {
  text-align: center;
  height: 100vh;
}
.el-main {
}
</style>
