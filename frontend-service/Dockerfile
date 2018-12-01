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
ENV PORT=8080

COPY ./entrypoint.sh .

ENTRYPOINT [ "./entrypoint.sh" ]

EXPOSE 8080

CMD ["yarn", "serve"]
