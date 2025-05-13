<script>
import {getCollections} from "@/services/collection.service";

export default {
  name: "CollectionList",
  data() {
    return {
      collections: []
    };
  },
  async mounted() {
    this.collections = await getCollections();
    console.log(this.collections);
  }
}

</script>

<template>
  <div class="collections">
    <h1> Collection List </h1>
<!--    {{ collections }}-->
    <div class="collection-card" v-for="collection in collections" :key="collection.id">
      {{ collection.title }}
      <p>Plants:</p>
      <div v-for="plant in collection.plants" :key="plant.id">
        {{plant}}
      </div>
    </div>
  </div>
</template>

<style scoped>

</style>