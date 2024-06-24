<template>
  <el-card>
    <el-row>
      <el-col :span="11">
        <div id="div1" style="height: 75vh;width:40vw;"></div>
      </el-col>
      <el-col :span="11">
        <div id="div2" style="height: 75vh;width:40vw;"></div>
      </el-col>
    </el-row>
  </el-card>
</template>

<script>
import axios from 'axios';

export default {
  name: "DisciplineAnalyse",
  data() {
    return {
      bcData: [],
      userData: []
    }
  },
  mounted() {
    this.init();
  },
  methods: {
    async init(){
      const res1 = await axios.get('//localhost:9999/analyse/stat_viobc');
      const res2 = await axios.get('//localhost:9999/analyse/stat_viobu');
      this.bcData = res1.data;
      this.userData = res2.data;
      let option1 = {
        title: {
          text: '各会议室违规次数分析',
          left: 'center'
        },
        tooltip: {
          trigger: 'item'
        },
        legend: {
          orient: 'vertical',
          left: 'left'
        },
        series: [
          {
            name: '违规次数',
            type: 'pie',
            radius: '50%',
            data: this.bcData,
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }
        ]
      };
      let option2 = {
        title: {
          text: '员工违规次数分析',
          left: 'center'
        },
        tooltip: {
          trigger: 'item'
        },
        legend: {
          orient: 'vertical',
          left: 'left'
        },
        series: [
          {
            name: '违规次数',
            type: 'pie',
            radius: '50%',
            data: this.userData,
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }
        ]
      };
      let myChart = this.$echarts.init(document.getElementById('div1'));
      window.onresize = myChart.resize;
      myChart.setOption(option1);
      let myChart2 = this.$echarts.init(document.getElementById('div2'));
      window.onresize = myChart2.resize;
      myChart2.setOption(option2);
    }
  }
}
</script>

<style scoped>

</style>
