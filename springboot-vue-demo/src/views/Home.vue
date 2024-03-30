<template>
  <div style="padding: 10px">
    <div style="margin: 10px 0">
      <el-button type="primary" @click="add">新增</el-button>
      <el-button type="primary">导入</el-button>
      <el-button type="primary">导出</el-button>
    </div>
    <div style="margin: 10px 0">
      <el-input v-model="search" placeholder="请输入内容" style="width: 20%;" clearable></el-input>
      <el-button type="primary" style="margin-left: 5px" @click="load">查询</el-button>
    </div>
    <el-table :data="tableData" border stripe style="width: 100%">
      <el-table-column prop="id" label="id" sortable/>
      <el-table-column prop="username" label="用户名" />
      <el-table-column prop="nickName" label="昵称" />
      <el-table-column prop="age" label="年龄" />
      <el-table-column prop="sex" label="性别" />
      <el-table-column prop="address" label="地址" />
      <el-table-column fixed="right" label="操作">
        <template #default="scope">
          <el-button size="mini" @click="handleEdit(scope.row)">编辑</el-button>
          <el-popconfirm title="确认删除吗？" @confirm="handleDelete(scope.row.id)">
            <template #reference>
              <el-button size="mini" type="danger">删除</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <div style="margin: 10px 0">
      <el-pagination
         v-model:current-page="currentPage"
         v-model:page-size="pageSize"
         :page-sizes="[5, 10, 20]"
         :small="small"
         :disabled="disabled"
         :background="background"
         layout="total, sizes, prev, pager, next, jumper"
         :total="total"
         @size-change="handleSizeChange"
         @current-change="handleCurrentChange"/>
      <el-dialog
              v-model="dialogVisible"
              title="新增"
              width="30%">
        <el-form :mode="form" label-width="120px">
          <el-form-item label="用户名">
            <el-input v-model="form.username"  style="width: 80%"/>
          </el-form-item>
          <el-form-item label="昵称">
            <el-input v-model="form.nickName"  style="width: 80%"/>
          </el-form-item>
          <el-form-item label="性别">
            <el-radio v-model="form.sex" value="男" size="large">男</el-radio>
            <el-radio v-model="form.sex" value="女" size="large">女</el-radio>
            <el-radio v-model="form.sex" value="未知" size="large">未知</el-radio>
          </el-form-item>
          <el-form-item label="年龄">
            <el-input v-model="form.age"  style="width: 80%"/>
          </el-form-item>
          <el-form-item label="地址">
            <el-input type="textarea" v-model="form.address"  style="width: 80%"/>
          </el-form-item>
        </el-form>
        <template #footer>
          <div class="dialog-footer">
            <el-button @click="dialogVisible = false">取消</el-button>
            <el-button type="primary" @click="save">确认</el-button>
          </div>
        </template>
      </el-dialog>

    </div>
  </div>
</template>

<script>
// @ is an alias to /src

import request from "@/utils/request";

export default {
  name: 'HomeView',
  components: {
  },
  data() {
    return {
      form: {},
      search:'',
      dialogVisible: false,
      tableData: [],
      currentPage: 1,
      pageSize: 10,
      total: 0,
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      request.get("/user", {
        params: {
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          search: this.search
        }
      }).then(res => {
        console.log(res)
        this.tableData = res.data.records
        this.total = res.data.total
      })
    },
    add() {
      this.dialogVisible = true
      this.form = {}
    },
    save() {
      if(this.form.id) {
        request.put("/user", this.form).then(res => {
          console.log(res)
          if(res.code === "0") {
            this.$message({
              type: "success",
              message: "更新成功"
            })
          } else {
            this.$message({
              type: "error",
              message: "更新失败"
            })
          }
          this.load()
          this.dialogVisible = false
        })
      } else {
        request.post("/user", this.form).then(res => {
          console.log(res)
          if(res.code === "0") {
            this.$message({
              type: "success",
              message: "新增成功"
            })
          } else {
            this.$message({
              type: "error",
              message: "新增失败"
            })
          }
          this.load()
          this.dialogVisible = false
        })
      }
    },
    handleEdit(row) {
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogVisible = true
    },
    handleDelete(id) {
      request.delete("/user/" + id).then(res => {
        if(res.code === "0") {
          this.$message({
            type: "success",
            message: "删除成功"
          })
        } else {
          this.$message({
            type: "error",
            message: "删除失败"
          })
        }
        this.load()
      })
    },
    handleSizeChange(pageSize) {
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum) {
      this.currentPage = pageNum
      this.load()
    }
  }
}
</script>
