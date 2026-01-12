import http from 'k6/http';
import { sleep } from 'k6';

const BASE_URL = 'http://localhost:8080';
const RESOURCE = '/posts';
const RESOURCE_ID = '/1';
const TYPE = {
    BASIC: '/basic',
    PESSIMISTIC: '/pessimistic',
    OPTIMISTIC: '/optimistic',
    ATOMIC: '/atomic',
    REDIS: '/redis',
}

const VIRTUAL_USERS = 100;
const DURATION = '1m';
const URL = `${BASE_URL}${RESOURCE}${RESOURCE_ID}${TYPE.BASIC}`;

export let options = {
    vus: VIRTUAL_USERS,
    duration: DURATION
}

export function setup() {
    console.log('Testing URL:', URL);
}

export default function () {
    http.get(URL);
    sleep(1);
}