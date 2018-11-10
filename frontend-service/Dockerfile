FROM node:8.11-alpine as Builder

WORKDIR /app

COPY ./package.json .
COPY ./yarn.lock .

RUN yarn install

COPY ./.babelrc .
COPY ./.editorconfig .
COPY ./.eslintrc .
COPY ./.prettierrc .
COPY ./index.js .
COPY ./static.config.js .
COPY ./src ./src
COPY ./public ./public

RUN ls -la

RUN yarn run build

FROM nginx:1.15-alpine

EXPOSE 8080

COPY --from=Builder /app/dist /var/www/html
COPY ./nginx.conf /etc/nginx/conf.d/default.conf