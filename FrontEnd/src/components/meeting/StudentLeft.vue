<template>
  <el-aside width="200px">
    <el-menu
      default-active="/home/meeting/myschedulelist"
      class="el-menu-vertical-demo"
      style="height: 100vh"
      :router="true"
    >
      <el-menu-item index="/home/meeting/myschedulelist">
        <i class="el-icon-menu"></i>
        <span slot="title">我的预定</span>
      </el-menu-item>
      <el-menu-item v-if="$currentUser.role==='管理员'" index="/home/meeting/schedulelist">
        <i class="el-icon-menu"></i>
        <span slot="title">预定记录</span>
      </el-menu-item>
      <el-menu-item v-if="$currentUser.role==='管理员'" index="/home/meeting/disciplinelist">
        <i class="el-icon-user"></i>
        <span slot="title">违规管理</span>
      </el-menu-item>
      <el-menu-item v-if="$currentUser.role==='管理员'" index="/home/meeting/meetinglist">
        <i class="el-icon-house"></i>
        <span slot="title">会议室管理</span>
      </el-menu-item>
    </el-menu>
  </el-aside>
</template>

<script>
export default {
name: 'StudentLeft'
}
</script>

<style scoped>

</style>
