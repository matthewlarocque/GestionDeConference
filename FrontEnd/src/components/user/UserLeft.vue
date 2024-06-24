<template>
  <el-aside width="200px">
    <el-menu
      default-active="/home/user/password"
      class="el-menu-vertical-demo"
      style="height: 100vh"
      :router="true"
    >
      <el-menu-item index="/home/user/password">
        <i class="el-icon-lock"></i>
        <span slot="title">修改密码</span>
      </el-menu-item>
      <el-menu-item index="/home/user/phone">
        <i class="el-icon-phone"></i>
        <span slot="title">修改联系方式</span>
      </el-menu-item>
      <el-menu-item v-if="$currentUser.role==='管理员'" index="/home/user/userlist">
        <i class="el-icon-user"></i>
        <span slot="title">用户管理</span>
      </el-menu-item>
    </el-menu>
  </el-aside>
</template>

<script>
export default {
name: 'UserLeft'
}
</script>

<style scoped>

</style>
