<template>
  <el-card>
    <el-row>
      <el-col :span="12">
        <div id="div1" style="height: 75vh;width:40vw;"></div>
      </el-col>
      <el-col :span="12">
        <div id="div2" style="height: 75vh;width:40vw;"></div>
      </el-col>
    </el-row>
  </el-card>
</template>

<script>
import axios from 'axios';

export default {
  name: "ScheduleAnalyse",
  data() {
    return {
      reserveChartData: [],
      chamberData: [],
      periodData: []
    }
  },
  mounted() {
    this.init();
  },
  methods: {
    async init(){
      const res1 = await axios.get('//localhost:9999/analyse/stat_reserve');
      const res2 = await axios.get("//localhost:9999/analyse/list_chambers");
      const res3 = await axios.get("//localhost:9999/analyse/list_period");
      this.reserveChartData = res1.data;
      this.chamberData = res2.data;
      this.periodData = res3.data;

      let option1 = {
        title: {
          text: '各会议室预定人数分析',
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
            name: '各会议室预定人数',
            type: 'pie',
            radius: '50%',
            data: this.reserveChartData,
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
      let option2 =  {
        title: {
          text: '各会议室使用时长分析',
          left: 'center'
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'cross',
            crossStyle: {
              color: '#999'
            }
          }
        },
        legend: {
          data: ['单位：小时'],
          left: 'left'
        },
        xAxis: [
          {
            type: 'category',
            data: this.chamberData,
            axisPointer: {
              type: 'shadow'
            }
          }
        ],
        yAxis: [
          {
            type: 'value',
          }
        ],
        series: [
          {
            type: 'bar',
            data: this.periodData
          },
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
