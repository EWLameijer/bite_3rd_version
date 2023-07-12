import bite from './bite';
import { postData } from './utils';

const basePath = bite.basePath + `baskets`;

const post = basket => postData(basePath, basket)

export { post }

