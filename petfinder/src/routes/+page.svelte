<script>
  // @ts-nocheck

  import {
    Button,
    Checkbox,
    Input,
    Label,
    Range,
    Select,
    Spinner,
  } from "flowbite-svelte";
  import {
    AdjustmentsVerticalOutline,
    BarsSolid,
    CheckCircleSolid,
    GearSolid,
    UserSettingsSolid,
    XCircleOutline,
  } from "flowbite-svelte-icons";
  import { onMount } from "svelte";
  import BarChart from "../components/BarChart.svelte";

  let amount = 10;

  let testing = false;

  let data = null;

  let method = "POST";

  let name = "";

  let referencing = false;

  let methods = [
    { value: "POST", name: "POST" },
    { value: "GET", name: "GET" },
  ];

  let title = "";

  async function doTest(fr) {}
</script>

<div
  class="flex flex-row-reverse gap-2.5 justify-center items-center w-screen h-screen bg-white dark:bg-slate-900"
>
  <div class="flex flex-col gap-5 bg-slate-50 rounded-xl p-3 self-auto">
    <div class="flex gap-1 items-center justify-center font-medium">
      <AdjustmentsVerticalOutline></AdjustmentsVerticalOutline> Settings
    </div>
    {#if method === "POST"}
      <Label>
        <span>Amount of Entities</span>
        <Input type="number" class="max-w-40" bind:value={amount}></Input>
      </Label>
    {/if}

    {#if method === "GET"}
      <Label>
        <span>Name</span>
        <Input type="text" class="max-w-40" bind:value={name}></Input>
      </Label>
    {/if}

    <Label>
      Method
      <Select class="mt-2" items={methods} bind:value={method} />
    </Label>
    <Checkbox bind:value={referencing}>Mongo Referencing</Checkbox>
    {#if method === "POST"}
      <Button
        on:click={async () => {
          if (testing) return;
          testing = true;
          const startMongo = performance.now();
          await fetch(
            "http://localhost:8080/api/" + (referencing
              ? "mongoReferencingOwners"
              : "mongoOwners") + "/test/" + amount,
            {
              method: "POST",
            }
          );
          const stopMongo = performance.now();

          const startJPA = performance.now();
          await fetch("http://localhost:8080/api/jpaOwners/test/" + amount, {
            method: "POST",
          });
          const stopJPA = performance.now();

          const mongoTime = (stopMongo - startMongo) / 1000;
          const jpaTime = (stopJPA - startJPA) / 1000;

          data = null;
          data = [
            { database: "Mongo", time: mongoTime },
            { database: "Postgres", time: jpaTime },
          ];
          title = "Time it took to insert " + amount + " Entities";
          testing = false;
        }}
      >
        {#if testing}
          <Spinner size={5}></Spinner>
        {:else}
          Start Test
        {/if}
      </Button>
    {:else}
      <Button
        on:click={async () => {
          if (testing) return;
          testing = true;
          const startMongo = performance.now();
          await fetch(
            "http://localhost:8080/api/" + (referencing
              ? "mongoReferencingOwners"
              : "mongoOwners") + "/name/" + name,
            {
              method: "GET",
            }
          );
          const stopMongo = performance.now();

          const startJPA = performance.now();
          await fetch("http://localhost:8080/api/jpaOwners/name/" + name, {
            method: "GET",
          });
          const stopJPA = performance.now();

          const mongoTime = (stopMongo - startMongo) / 1000;
          const jpaTime = (stopJPA - startJPA) / 1000;

          data = null;
          data = [
            { database: "Mongo", time: mongoTime },
            { database: "Postgres", time: jpaTime },
          ];
          title = "Time it took to find " + name;
          testing = false;
        }}
      >
        {#if testing}
          <Spinner size={5}></Spinner>
        {:else}
          Start Test
        {/if}
      </Button>
    {/if}
  </div>
  <div
    class="bg-slate-50 flex flex-col items-center justify-center dark:bg-slate-800 p-3 rounded-xl w-[1100px] h-[600px]"
  >
    <h1 class="font-medium">{title}</h1>
    {#if data !== null}
      {#key data}
        <BarChart {data}></BarChart>
        <div class="text-sm gap-1 flex justify-center items-center">Postgres takes  <p class={Math.floor((data[1].time/data[0].time-1)*100) > 0 ? "text-green-500" : "text-red-500"}> {Math.floor((data[1].time/data[0].time-1)*100)}%</p> {Math.floor((data[1].time/data[0].time-1)*100) > 0 ? "longer" : "less"} than Mongo</div>

      {/key}
    {/if}
  </div>
</div>
