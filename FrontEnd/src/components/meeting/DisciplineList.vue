<template>
  <el-container>
    <el-header style="display: flex;justify-content: space-between">
      <el-form :inline="true" :model="searchObj" class="demo-form-inline">
        <el-form-item label="员工名">
          <el-input v-model="searchObj.realname" placeholder="员工名"></el-input>
        </el-form-item>
        <el-form-item label="违规事由">
          <el-select clearable v-model="searchObj.content" placeholder="违规事由" style="width: 140px">
            <el-option value="拖延时间">拖延时间</el-option>
            <el-option value="毁坏物品">毁坏物品</el-option>
            <el-option value="其他">其他</el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="处罚方式" prop="punishment">
          <el-select clearable v-model="searchObj.punishment" placeholder="处罚方式" style="width: 140px">
            <el-option value="口头警告">口头警告</el-option>
            <el-option value="拉黑处理">拉黑处理</el-option>
            <el-option value="其他">其他</el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSearch">查询</el-button>
        </el-form-item>
      </el-form>
      <el-form :inline="true" class="demo-form-inline">
        <el-form-item>
          <el-button type="primary" @click="addDiscipline">新增违规</el-button>
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
          label="违规员工"
          property="realname"
          width="100"
        >
        </el-table-column>
        <el-table-column
          label="违规事由"
          property="content"
        >
        </el-table-column>
        <el-table-column
          label="处罚方式"
          property="punishment"
        >
        </el-table-column>
        <el-table-column
          label="备注"
          property="remark"
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
      <DisciplineForm :id="id" :dialog-form-visible="dialogFormVisible" :size-per-page="pageObj.pageSize" v-on:disciplineList="getDisciplineList"></DisciplineForm>
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
import DisciplineForm from './DisciplineForm';
export default {
  name: 'DisciplineList',
  components: {DisciplineForm},
  data() {
    return {
      id: null,
      searchObj: {
        name: '',
      },
      pageObj: {
        total:0,
        pagerCount: 0,
        pageSize:8,
        currentPage:0
      },
      dialogFormVisible:false,
      tableData: [],
    }
  },
  mounted(){
    this.getDisciplineList({currentPage:0,sizePerPage:this.pageObj.pageSize})
  },
  methods: {
    getDisciplineList(obj) {
      postAction('discipline/list', obj).then((res) => {
        this.pageObj.total=res.data.total;
        this.pageObj.pagerCount=res.data.totalPages;
        this.tableData=res.data.list;
      });
    },
    addDiscipline(index) {
      this.id=null;
      this.dialogFormVisible=false;
      setTimeout(()=>{
        this.dialogFormVisible=true
      },100)
    },
    onPrev(index) {
      this.pageObj.currentPage=index-1
      this.getDisciplineList({currentPage:index-1,sizePerPage:this.pageObj.pageSize})
    },
    onNext(index) {
      this.pageObj.currentPage=index-1
      this.getDisciplineList({currentPage:index-1,sizePerPage:this.pageObj.pageSize})
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
        postAction('discipline/deleteDiscipline', {id:row.id}).then((res) => {
          this.$message.success('删除成功')
          this.getDisciplineList({currentPage:0,sizePerPage:this.pageObj.pageSize})
        });
      }).catch(() => {
      });
    },
    onSearch() {
      this.getDisciplineList({
        currentPage:0,
        sizePerPage:this.pageObj.pageSize,
        content:this.searchObj.content,
        punishment:this.searchObj.punishment,
      })
    },
  }
}
</script>

<style scoped>

</style>
