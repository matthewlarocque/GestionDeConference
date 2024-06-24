<template>
  <el-aside width="200px">
    <el-menu
      default-active="/home/analyse/schedule"
      class="el-menu-vertical-demo"
      style="height: 100vh"
      :router="true"
    >
      <el-menu-item index="/home/analyse/schedule">
        <i class="el-icon-menu"></i>
        <span slot="title">预定分析</span>
      </el-menu-item>
      <el-menu-item index="/home/analyse/discipline">
        <i class="el-icon-s-data"></i>
        <span slot="title">违规分析</span>
      </el-menu-item>
      <el-menu-item index="/home/analyse/user">
        <i class="el-icon-user"></i>
        <span slot="title">员工分析</span>
      </el-menu-item>
    </el-menu>
  </el-aside>
</template>

<script>
export default {
name: 'AnalyseLeft'
}
</script>

<style scoped>

</style>
