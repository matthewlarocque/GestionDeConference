<template>
  <el-container>
    <el-header style="display: flex;justify-content: space-between">
      <el-form :inline="true" :model="searchObj" class="demo-form-inline">
        <el-form-item label="会议室名称">
          <el-input v-model="searchObj.name" placeholder="会议室名称"></el-input>
        </el-form-item>
        <el-form-item label="会议室规格">
          <el-select v-model="searchObj.type" placeholder="请选择规格">
            <el-option value="大型">大型</el-option>
            <el-option value="中型">中型</el-option>
            <el-option value="小型">小型</el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSearch">查询</el-button>
        </el-form-item>
      </el-form>
      <el-form :inline="true" class="demo-form-inline">
        <el-form-item>
          <el-button type="primary" @click="addMeeting">新增会议室</el-button>
        </el-form-item>
      </el-form>
    </el-header>
    <el-main>
      <el-table
        :data="tableData"
        style="width: 100%">
        <el-table-column
          type="index"
          width="50">
        </el-table-column>
        <el-table-column
          label="会议室名称"
          property="name"
        >
        </el-table-column>
        <el-table-column
          label="规格"
          property="type"
        >
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button
              size="mini"
              @click="handleEdit(scope.$index, scope.row)">编辑
            </el-button>
            <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)">删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <MeetingForm :id="id" :dialog-form-visible="dialogFormVisible" :size-per-page="pageObj.pageSize" v-on:meetingList="getMeetingList"></MeetingForm>
    </el-main>
    <el-footer style="text-align: right">
      <el-pagination
        :page-size="pageObj.pageSize"
        :page-count="pageObj.pagerCount"
        :pager-count="11"
        @prev-click="onPrev"
        @next-click="onNext"
        @current-change="onPrev"
        :hide-on-single-page="true"
        background
        layout="prev, pager, next"
        :total="pageObj.total">
      </el-pagination>
    </el-footer>

  </el-container>
</template>

<script>
import { postAction} from '@api/manage';
import MeetingForm from './MeetingForm';
import Vue from 'vue';
export default {
  name: 'MeetingList',
  components: {MeetingForm},
  data() {
    return {
      id: null,
      searchObj: {
        name: '',
        type:undefined
      },
      pageObj: {
        total:0,
        pagerCount: 0,
        pageSize:8,
        currentPage:0
      },
      dialogFormVisible:false,
      tableData: [],
      users: [],
    }
  },
  mounted(){
    this.getMeetingList({currentPage:0,sizePerPage:this.pageObj.pageSize})
  },
  methods: {
    getMeetingList(obj) {
      postAction('meeting/list', obj).then((res) => {
        this.pageObj.total=res.data.total;
        this.pageObj.pagerCount=res.data.totalPages;
        this.tableData=res.data.list;
      });
    },
    addMeeting(index) {
      this.id=null;
      this.dialogFormVisible=false;
      setTimeout(()=>{
        this.dialogFormVisible=true
      },100)
    },
    onPrev(index) {
      this.pageObj.currentPage=index-1
      this.getMeetingList({currentPage:index-1,sizePerPage:this.pageObj.pageSize})
    },
    onNext(index) {
      this.pageObj.currentPage=index-1
      this.getMeetingList({currentPage:index-1,sizePerPage:this.pageObj.pageSize})
    },
    handleEdit(index, row) {
      this.id='';
      this.id=row.id;
      this.dialogFormVisible=false;
      setTimeout(()=>{
        this.dialogFormVisible=true
      },100)
    },
    handleDelete(index, row) {
      this.$confirm('确定要删除吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        if(row.uses!=null&&row.uses!==''){
          this.$message.error('该会议室已有使用记录，无法删除')
        }else{
          postAction('meeting/deleteMeeting', {id:row.id}).then((res) => {
            this.$message.success('删除成功')
            this.getMeetingList({currentPage:0,sizePerPage:this.pageObj.pageSize})
          });
        }
      }).catch(() => {
      });
    },
    onSearch() {
      this.getMeetingList({currentPage:0,
        sizePerPage:this.pageObj.pageSize,
        name:this.searchObj.name,
        type:this.searchObj.type
       })
    },
  }
}
</script>

<style scoped>

</style>
