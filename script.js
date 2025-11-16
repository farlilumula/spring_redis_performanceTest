import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
    stages: [
        { duration: '10s', target: 50 },   // Ramp-up ke 50 VU
        { duration: '50s', target: 100 },  // Stabil 100 VU
        { duration: '10s', target: 0 },    // Ramp-down ke 0
    ],
    thresholds: {
        http_req_duration: ['p(95)<200'], // 95% < 200ms
        http_req_failed: ['rate<0.01'],   // Error < 1%
    },
};

const BASE_URL = 'http://localhost:8080';

export default function () {
    const res = http.get(`${BASE_URL}/api/categories`);

    check(res, {
        'status is 200': (r) => r.status === 200,
        'response has data': (r) => {
            try {
                const data = JSON.parse(r.body);
                return Array.isArray(data) && data.length > 0;
            } catch (e) {
                return false;
            }
        },
    });

    sleep(Math.random() * 1.5 + 0.5); // Real user delay
}