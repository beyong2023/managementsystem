<template>
  <div>
      <el-menu
              style="width: 200px; min-height: calc(100vh - 50px)"
              default-active="2"
              :default-oppedds="1"
              class="el-menu-vertical-demo">
          <el-sub-menu index="1-4">
              <template #title>系统管理</template>
              <el-menu-item index="user">用户管理</el-menu-item>
          </el-sub-menu>
      </el-menu>
  </div>
</template>

<script>
import {
    Document,
    Menu as IconMenu,
    Location,
    Setting,
} from '@element-plus/icons-vue'
export default {
    name: "Aside",
    components: {
        Document,
        IconMenu,
        Location,
        Setting
    }
}
</script>

<style scoped>

</style>