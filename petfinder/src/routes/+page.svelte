<script>
  // @ts-nocheck

  import { Button, Input, Label, Range, Spinner } from "flowbite-svelte";
  import {
    BarsSolid,
    CheckCircleSolid,
    XCircleOutline,
  } from "flowbite-svelte-icons";
  import { onMount } from "svelte";
  import BarChart from "../components/BarChart.svelte";

  let amount = 10;

  let testing = false;

  let data = null;

  async function doTest(fr) {}
</script>

<div
  class="flex flex-col gap-2.5 justify-center items-center w-screen h-screen"
>
  <Label>
    <span>Amount of Inserts</span>
    <Input type="number" class="max-w-40" bind:value={amount}></Input>
  </Label>
  <Button
    on:click={async () => {
      if (testing) return;
      const startMongo = performance.now();
      await fetch("http://localhost:8080/api/owners/test/" + amount, {
        method: "POST",
      });
      const stopMongo = performance.now();

      const startJPA = performance.now();
      await fetch("http://localhost:8080/api/ownersJPA/test/" + amount, {
        method: "POST",
      });
      const stopJPA = performance.now();

      const mongoTime =  (stopMongo - startMongo) / 1000;
      const jpaTime = (stopJPA - startJPA) / 1000;
      
      data = null;
      data = [
        { database: "mongo", time: mongoTime },
        { database: "postgres", time: jpaTime},
      ];
    }}
  >
    {#if testing}
      <Spinner></Spinner>
    {:else}
      Start Test
    {/if}
  </Button>
  {#if data !== null}
    <BarChart {data}></BarChart>
  {/if}
</div>
