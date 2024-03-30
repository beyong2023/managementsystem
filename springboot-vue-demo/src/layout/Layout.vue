<template>
    <div>
        <Header/>
        <div style="display: flex">
            <Aside/>
            <router-view style="flex: 1"/>
        </div>
    </div>
</template>

<script>
import Aside from "@/components/Aside.vue";
import Header from "@/components/Header.vue";

export default {
    name: "Layout",
    components: {Header, Aside}
}
</script>

<style scoped>

</style>